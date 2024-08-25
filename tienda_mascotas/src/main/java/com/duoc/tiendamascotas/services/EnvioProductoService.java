package com.duoc.tiendamascotas.services;

import com.duoc.tiendamascotas.entities.Envio;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface EnvioProductoService {

    List<Envio> obtenerEnvios();
    Envio consultarEnvioById(int id);
    String consultarUbicacion(int idEnvio);


    //Venta registrarVenta(int idProducto, int cantidad, Date fechaVenta);
}
