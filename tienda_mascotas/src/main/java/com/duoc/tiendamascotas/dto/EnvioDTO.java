package com.duoc.tiendamascotas.dto;

import com.duoc.tiendamascotas.entities.Producto;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class EnvioDTO {

    @NotNull(message = "El campo listaProductos no puede estar vacío")
    private List<Producto> listaProductos;

    @NotNull(message = "El campo ubicacionActual no puede estar vacío")
    @Size(min = 2, max = 100, message = "El campo ubicacionActual debe tener entre 2 y 100 caracteres")
    private String ubicacionActual;

    @NotNull(message = "El campo destino no puede estar vacío")
    @Size(min = 10, max = 100, message = "El campo destino debe tener entre 10 y 100 caracteres")
    private String destino;


}
