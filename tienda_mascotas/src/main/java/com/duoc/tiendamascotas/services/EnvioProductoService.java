package com.duoc.tiendamascotas.services;

import com.duoc.tiendamascotas.dto.EnvioDTO;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface EnvioProductoService {

    void generarEnvio(EnvioDTO envioDTO);
    List<EnvioDTO> obtenerEnvios();
    Optional<EnvioDTO> consultarEnvioById(int id);
    String consultarUbicacion(int idEnvio);
    EnvioDTO consultarDetalleEnvio(int idDetalleEnvio);


    //Venta registrarVenta(int idProducto, int cantidad, Date fechaVenta);
}
