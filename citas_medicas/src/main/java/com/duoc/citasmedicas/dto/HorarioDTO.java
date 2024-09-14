package com.duoc.citasmedicas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HorarioDTO {

    @NotNull(message = "El campo idHorario puede estar vacío")
    private Integer idHorario;
    private LocalDateTime fechaHora;
    private boolean disponible;

}
