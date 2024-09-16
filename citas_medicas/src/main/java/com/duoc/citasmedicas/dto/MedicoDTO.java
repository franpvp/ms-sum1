package com.duoc.citasmedicas.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoDTO {

    @NotNull(message = "El campo idMedico no puede estar vacío")
    @Positive(message = "El campo idMedico debe ser un número positivo")
    private Integer idMedico;

    @Size(min = 10, max = 12, message = "El campo rut debe tener entre 10 y 12 caracteres")
    @NotNull(message = "El campo rut no puede estar vacío")
    private String rut;

    @Size(min = 3, max = 50, message = "El campo nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo nombre solo puede contener letras")
    @NotNull(message = "El campo nombre no puede estar vacío")
    private String nombre;

    @Size(min = 5, max = 50, message = "El campo apellidoPaterno debe tener entre 5 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellidoPaterno solo puede contener letras")
    @NotNull(message = "El campo apellidoPaterno no puede estar vacío")
    private String apellidoPaterno;

    @Size(min = 5, max = 50, message = "El nombre debe tener entre 5 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El campo apellidoMaterno solo puede contener letras")
    @NotNull(message = "El campo apellidoMaterno no puede estar vacío")
    private String apellidoMaterno;

    @NotNull(message = "El campo idEspecialidad no puede estar vacío")
    @Positive(message = "El campo idEspecialidad debe ser un número positivo")
    private int idEspecialidad;

}
