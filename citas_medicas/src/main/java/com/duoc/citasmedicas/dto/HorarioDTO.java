package com.duoc.citasmedicas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HorarioDTO {

    @NotNull(message = "El campo idHorario puede estar vacío")
    @Positive(message = "El campo idHorario debe ser un número positivo")
    private Integer idHorario;

    @NotNull(message = "El campo fechaHora puede estar vacío")
    private LocalDateTime fechaHora;
    
    @NotNull(message = "El campo disponible puede estar vacío")
    private boolean disponible;

}
