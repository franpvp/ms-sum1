package com.duoc.citasmedicas.dto;

import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.entities.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitaMedicaDTO {

    @JsonProperty(value = "paciente")
    @NotNull(message = "El campo paciente no puede estar vacío")
    private PacienteDTO pacienteDTO;

    @JsonProperty(value = "medico")
    @NotNull(message = "El campo médico no puede estar vacío")
    private MedicoDTO medicoDTO;

    @JsonProperty(value = "horario")
    @NotNull(message = "El campo horario no puede estar vacío")
    private HorarioDTO horarioDTO;

}
