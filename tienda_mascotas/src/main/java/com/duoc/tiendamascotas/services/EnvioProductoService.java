package com.duoc.tiendamascotas.services;

import com.duoc.tiendamascotas.dto.DetalleEnvioProductoDTO;
import com.duoc.tiendamascotas.dto.EnvioDTO;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface EnvioProductoService {

    void generarEnvio(EnvioDTO envioDTO);
    void modificarEstadoEnvio(int idEnvio, int idEstadoEnvio);
    List<EnvioDTO> obtenerEnvios();
    Optional<EnvioDTO> consultarEnvioById(int id);
    String consultarUbicacion(int idEnvio);
    String eliminarEnvio(int idEnvio);


}
