package com.duoc.citasmedicas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitaMedicaDTO {

    @JsonProperty(value = "idCita")
    @Positive(message = "El campo idCita debe ser un número positivo")
    private Integer idCita;

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
