package com.duoc.citasmedicas.services;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.entities.PacienteEntity;
import com.duoc.citasmedicas.exceptions.CitaMedicaNotFoundException;
import com.duoc.citasmedicas.exceptions.HorarioNotAvailableException;
import com.duoc.citasmedicas.exceptions.IllegalNumberException;
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

    @Override
    public List<CitaMedicaDTO> obtenerCitasMedicas() {
        List<CitaMedicaEntity> citasMedicas = citaMedicaRepository.findAll();

        if (citasMedicas.isEmpty()) {
            throw new CitaMedicaNotFoundException("No se encontraron citas médicas");
        }

        return citasMedicas.stream()
                .map(citaMedicaEntity -> citaMapper.citaEntityToDTO(citaMedicaEntity))
                .toList();
    }

    @Override
    public Optional<CitaMedicaDTO> obtenerCitaMedicaById(int id) {

        if (id <= 0) {
            throw new IllegalNumberException("El ID del envío debe ser positivo y no nulo");
        }
        return Optional.ofNullable(citaMedicaRepository.findById(id)
                .map(citaMedicaEntity -> citaMapper.citaEntityToDTO(citaMedicaEntity))
                .orElseThrow(() -> new CitaMedicaNotFoundException("Cita médica no encontrada con id: " + id)));
    }

    @Override
    public List<HorarioDTO> obtenerDisponibilidadHorarios() {
        return horarioRepository.findAll().stream().map(horarioEntity ->
                citaMapper.horarioEntityToDTO(horarioEntity)
        ).toList();
    }

    @Override
    public CitaMedicaDTO crearCitaMedica(CitaMedicaDTO citaMedicaDTO) {

        // Validar que el idCita no sea nulo ni inválido (si se requiere que esté presente)
        if (citaMedicaDTO.getIdCita() != null && citaMedicaDTO.getIdCita() <= 0) {
            throw new IllegalNumberException("El ID de la cita médica debe ser positivo y no nulo");
        }

        // Valida que el paciente, medico y horario existan
        PacienteEntity pacienteEntity = pacienteRepository.findById(citaMedicaDTO.getPacienteDTO().getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        MedicoEntity medicoEntity = medicoRepository.findById(citaMedicaDTO.getMedicoDTO().getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        HorarioEntity horarioEntity = horarioRepository.findById(citaMedicaDTO.getHorarioDTO().getIdHorario())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        // Verifica si el horario está disponible
        if (!horarioEntity.isDisponible()) {
            throw new HorarioNotAvailableException("Horario ingresado no disponible: " + horarioEntity);
        }

        // Marca el horario como no disponible
        horarioEntity.setDisponible(false);
        horarioRepository.save(horarioEntity);

        try {
            // Crear el objeto CitaMedicaEntity sin idCita si es autogenerado
            CitaMedicaEntity citaMedicaEntity = CitaMedicaEntity.builder()
                    .pacienteEntity(pacienteEntity)
                    .medicoEntity(medicoEntity)
                    .horarioEntity(horarioEntity)
                    .build();

            // Guardar la entidad en la base de datos
            CitaMedicaEntity citaMedicaEntityGuardado = citaMedicaRepository.save(citaMedicaEntity);

            // Convertir a DTO para retornar con el ID generado
            return citaMapper.citaEntityToDTO(citaMedicaEntityGuardado);

        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear la cita médica", ex);
        }
    }

    @Override
    public CitaMedicaDTO modificarCitaMedica(int idCita, CitaMedicaDTO nuevaCitaDto) {

        PacienteEntity pacienteEntity = pacienteRepository.findById(nuevaCitaDto.getPacienteDTO().getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        MedicoEntity medicoEntity = medicoRepository.findById(nuevaCitaDto.getMedicoDTO().getIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        HorarioEntity horarioEntity = horarioRepository.findById(nuevaCitaDto.getHorarioDTO().getIdHorario())
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        if (idCita <= 0) {
            throw new IllegalNumberException("El ID de la cita médica debe ser positivo y no nulo");
        }
        return citaMedicaRepository.findById(idCita)
                .map(cita -> {
                    cita.setPaciente(pacienteEntity);
                    cita.setMedico(medicoEntity);
                    cita.setHorario(horarioEntity);
                    return citaMapper.citaEntityToDTO(citaMedicaRepository.save(cita));
                })
                .orElseThrow(() -> new CitaMedicaNotFoundException("Cita médica no encontrada con id: " + idCita));
    }

    @Override
    public void eliminarCitaMedicaById(int idCita) {

        if (idCita <= 0) {
            throw new IllegalNumberException("El ID de la cita médica debe ser positivo y no nulo");
        }
        CitaMedicaEntity citaMedicaEntity = citaMedicaRepository.findById(idCita)
                .orElseThrow(() -> new CitaMedicaNotFoundException("Cita médica no encontrada con id: " + idCita));

        HorarioEntity horarioEntity = citaMedicaEntity.getHorario();
        if (horarioEntity != null) {
            // Marca el horario como disponible
            horarioEntity.setDisponible(true);
            horarioRepository.save(horarioEntity);
        } else {
            throw new HorarioNotAvailableException("Horario asociado a la cita médica no encontrado: " + horarioEntity);
        }
        citaMedicaRepository.deleteById(idCita);
    }

}
