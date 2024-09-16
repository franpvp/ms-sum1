package com.duoc.citasmedicas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteDTO {

    @NotNull(message = "El campo idPaciente no puede estar vacío")
    @Positive(message = "El campo idPaciente debe ser un número positivo")
    private Integer idPaciente;

    @Size(min = 10, max = 12, message = "El campo rut debe tener entre 10 y 12 caracteres")
    private String rut;

    @Size(min = 3, max = 50, message = "El campo nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo nombre solo puede contener letras")
    private String nombre;

    @Size(min = 5, max = 50, message = "El campo apellidoPaterno debe tener entre 5 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellidoPaterno solo puede contener letras")
    private String apellidoPaterno;

    @Size(min = 5, max = 50, message = "El apellidoMaterno debe tener entre 5 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellidoMaterno solo puede contener letras")
    private String apellidoMaterno;

    @NotNull(message = "El campo idPrevision no puede estar vacío")
    @Positive(message = "El campo idPrevision debe ser un número positivo")
    private int idPrevision;

}
