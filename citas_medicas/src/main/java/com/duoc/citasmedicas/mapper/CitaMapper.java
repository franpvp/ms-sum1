package com.duoc.citasmedicas.mapper;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.dto.MedicoDTO;
import com.duoc.citasmedicas.dto.PacienteDTO;
import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.entities.PacienteEntity;
import org.springframework.stereotype.Component;

@Component
public class CitaMapper {

    // ENTRA ENTITY Y SALE DTO
    public CitaMedicaDTO citaEntityToDTO(CitaMedicaEntity citaMedicaEntity) {
        return CitaMedicaDTO.builder()
                .pacienteDTO(pacienteEntityToDTO(citaMedicaEntity.getPaciente()))
                .medicoDTO(medicoEntityToDTO(citaMedicaEntity.getMedico()))
                .horarioDTO(horarioEntityToDTO(citaMedicaEntity.getHorario()))
                .build();
    }

    public PacienteDTO pacienteEntityToDTO(PacienteEntity pacienteEntity) {
        return PacienteDTO.builder()
                .idPaciente(pacienteEntity.getIdPaciente())
                .rut(pacienteEntity.getRut())
                .nombre(pacienteEntity.getNombre())
                .apellidoPaterno(pacienteEntity.getApellidoPaterno())
                .apellidoMaterno(pacienteEntity.getApellidoMaterno())
                .idPrevision(pacienteEntity.getIdPrevision())
                .build();
    }

    public MedicoDTO medicoEntityToDTO(MedicoEntity medicoEntity) {
        return MedicoDTO.builder()
                .idMedico(medicoEntity.getIdMedico())
                .rut(medicoEntity.getRut())
                .nombre(medicoEntity.getNombre())
                .apellidoPaterno(medicoEntity.getApellidoPaterno())
                .apellidoMaterno(medicoEntity.getApellidoMaterno())
                .idEspecialidad(medicoEntity.getIdEspecialidad())
                .build();
    }

    public HorarioDTO horarioEntityToDTO(HorarioEntity horarioEntity) {
        return HorarioDTO.builder()
                .idHorario(horarioEntity.getIdHorario())
                .fechaHora(horarioEntity.getFechaHora())
                .disponible(horarioEntity.isDisponible())
                .build();
    }

    // ENTRA DTO Y SALE ENTITY
    public CitaMedicaEntity citaDtoToEntity(CitaMedicaDTO citaMedicaDTO) {
        return CitaMedicaEntity.builder()
                .pacienteEntity(pacienteDtoToEntity(citaMedicaDTO.getPacienteDTO()))
                .medicoEntity(medicoDtoToEntity(citaMedicaDTO.getMedicoDTO()))
                .horarioEntity(horarioDtoToEntity(citaMedicaDTO.getHorarioDTO()))
                .build();
    }

    public PacienteEntity pacienteDtoToEntity(PacienteDTO pacienteDTO) {
        return PacienteEntity.builder()
                .idPaciente(pacienteDTO.getIdPaciente())
                .rut(pacienteDTO.getRut())
                .nombre(pacienteDTO.getNombre())
                .apellidoPaterno(pacienteDTO.getApellidoPaterno())
                .apellidoMaterno(pacienteDTO.getApellidoMaterno())
                .idPrevision(pacienteDTO.getIdPrevision())
                .build();
    }

    public MedicoEntity medicoDtoToEntity(MedicoDTO medicoDTO) {
        return MedicoEntity.builder()
                .idMedico(medicoDTO.getIdMedico())
                .rut(medicoDTO.getRut())
                .nombre(medicoDTO.getNombre())
                .apellidoPaterno(medicoDTO.getApellidoPaterno())
                .apellidoMaterno(medicoDTO.getApellidoMaterno())
                .idEspecialidad(medicoDTO.getIdEspecialidad())
                .build();
    }

    public HorarioEntity horarioDtoToEntity(HorarioDTO horarioDTO) {
        return HorarioEntity.builder()
                .idHorario(horarioDTO.getIdHorario())
                .fechaHora(horarioDTO.getFechaHora())
                .disponible(horarioDTO.isDisponible())
                .build();
    }

}
