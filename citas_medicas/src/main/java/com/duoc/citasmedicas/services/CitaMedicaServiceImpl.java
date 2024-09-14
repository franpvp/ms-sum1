package com.duoc.citasmedicas.services;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.entities.PacienteEntity;
import com.duoc.citasmedicas.exceptions.CitaMedicaNotFoundException;
import com.duoc.citasmedicas.mapper.CitaMapper;
import com.duoc.citasmedicas.repositories.CitaMedicaRepository;
import com.duoc.citasmedicas.repositories.HorarioRepository;
import com.duoc.citasmedicas.repositories.MedicoRepository;
import com.duoc.citasmedicas.repositories.PacienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

    // Llamar CitaMedicaRepository
    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private CitaMapper citaMapper;

    // Programar nuevas citas, cancelarlas y consultar la disponibilidad de horarios.
    @Override
    public Optional<CitaMedicaDTO> obtenerCitaMedica(int id) {
        return citaMedicaRepository.findById(id).map(citaMedicaEntity ->
                citaMapper.citaEntityToDTO(citaMedicaEntity)
        );
    }

    @Override
    public List<HorarioDTO> obtenerDisponibilidadHorarios() {
        return horarioRepository.findAll().stream().map(horarioEntity ->
                citaMapper.horarioEntityToDTO(horarioEntity)
        ).toList();
    }

    @Override
    public void crearCitaMedica(CitaMedicaDTO citaMedicaDTO) {

        // Valida que el paciente, medico y horario existan
        PacienteEntity pacienteEntity = pacienteRepository.findById(citaMedicaDTO.getPacienteDTO().getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        MedicoEntity medicoEntity = medicoRepository.findById(citaMedicaDTO.getMedicoDTO().getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        HorarioEntity horarioEntity = horarioRepository.findById(citaMedicaDTO.getHorarioDTO().getIdHorario())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        // Verifica si el horario está disponible
        if (!horarioEntity.isDisponible()) {
            throw new RuntimeException("El horario no está disponible");
        }

        // Marca el horario como no disponible
        horarioEntity.setDisponible(false);
        horarioRepository.save(horarioEntity);

        try {
            CitaMedicaEntity citaMedicaEntityGuardado = citaMedicaRepository.save(CitaMedicaEntity.builder()
                    .pacienteEntity(pacienteEntity)
                    .medicoEntity(medicoEntity)
                    .horarioEntity(horarioEntity)
                    .build());
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear cita medica");
        }
    }

    @Override
    public CitaMedicaDTO modificarCitaMedica(int id_cita, CitaMedicaDTO nuevaCitaDto) {
        return citaMedicaRepository.findById(id_cita)
                .map(cita -> {
                    cita.setPaciente(citaMapper.pacienteDtoToEntity(nuevaCitaDto.getPacienteDTO()));
                    cita.setMedico(citaMapper.medicoDtoToEntity(nuevaCitaDto.getMedicoDTO()));
                    cita.setHorario(citaMapper.horarioDtoToEntity(nuevaCitaDto.getHorarioDTO()));
                    return citaMapper.citaEntityToDTO(citaMedicaRepository.save(cita));
                })
                .orElseThrow(() -> new CitaMedicaNotFoundException("Cita médica no encontrada con id: " + id_cita));
    }

    @Override
    public void eliminarCitaMedicaById(int id_cita) {

        if (id_cita <= 0) {
            throw new IllegalArgumentException("El ID de la cita médica debe ser positivo y no nulo");
        }
        CitaMedicaEntity citaMedicaEntity = citaMedicaRepository.findById(id_cita)
                .orElseThrow(() -> new RuntimeException("Cita médica no encontrada"));

        HorarioEntity horarioEntity = citaMedicaEntity.getHorario();
        if (horarioEntity != null) {
            // Marca el horario como disponible
            horarioEntity.setDisponible(true);
            horarioRepository.save(horarioEntity);
        } else {
            throw new RuntimeException("Horario asociado a la cita médica no encontrado");
        }

        citaMedicaRepository.deleteById(id_cita);
    }

}
