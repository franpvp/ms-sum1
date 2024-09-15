package com.duoc.tiendamascotas.services;

import com.duoc.tiendamascotas.dto.EnvioDTO;
import com.duoc.tiendamascotas.entities.DetalleEnvioProductoEntity;
import com.duoc.tiendamascotas.entities.EnvioEntity;
import com.duoc.tiendamascotas.entities.ProductoEntity;
import com.duoc.tiendamascotas.mapper.EnvioMapper;
import com.duoc.tiendamascotas.repositories.DetalleEnvioProductoRepository;
import com.duoc.tiendamascotas.repositories.EnvioRepository;
import com.duoc.tiendamascotas.repositories.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnvioProductoServiceImpl implements EnvioProductoService {

    @Autowired
    private EnvioRepository envioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleEnvioProductoRepository detalleEnvioProductoRepository;

    @Autowired
    private EnvioMapper envioMapper;

    public EnvioProductoServiceImpl() {

        // Inicializar lista de envios
//        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(0), productos.get(1), productos.get(2)), "En Bodega", "Calle Nueva York 123, Santiago, Región Metropolitana"));
//        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(4), productos.get(1)), "Entregado", "Avenida del Mar 456, La Serena, Coquimbo"));
//        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(3), productos.get(2)), "En Bodega", "Calle Prat 789, Valparaíso, Valparaíso"));
//        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(0), productos.get(4)), "En Bodega", "Avenida Alemania 101, Temuco, La Araucanía"));
//        envios.add(new Envio(envioIdCounter.getAndIncrement(), List.of(productos.get(1), productos.get(4)), "En Camino Destino", "Calle Colón 202, Punta Arenas, Magallanes"));
    }

    // Registrar nuevos envíos, actualizar su estado y consultar la ubicación actual
    @Override
    public void generarEnvio(EnvioDTO envioDTO) {

        try {
            EnvioEntity envioEntityGuardado = envioRepository.save(EnvioEntity.builder()
                            .ubicacionActual(envioDTO.getUbicacionActual())
                            .destino(envioDTO.getDestino())
                            .idEstadoEnvio(envioDTO.getIdEstadoEnvio())
                            .build());
            envioDTO.getListaIdProducto().forEach(idProd -> {
                DetalleEnvioProductoEntity detalleEnvioProductoEntity = detalleEnvioProductoRepository.save(DetalleEnvioProductoEntity.builder()
                                .idEnvio(envioEntityGuardado.getIdEnvio())
                                .idProducto(idProd)
                        .build());
            });
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear el envio");
        }
    }

    @Override
    public List<EnvioDTO> obtenerEnvios() {
        return envioRepository.findAll().stream().map(envioEntity ->
                envioMapper.envioEntityToDTO(envioEntity)).toList();
    }

    @Override
    public Optional<EnvioDTO> consultarEnvioById(int id) {
        return envioRepository.findById(id).map(envioEntity ->
                envioMapper.envioEntityToDTO(envioEntity));
    }

    public String consultarUbicacion(int idEnvio) {
        String envioDTO = envioRepository.findById(idEnvio).stream().map(envioEntity ->
                envioMapper.envioEntityToDTO(envioEntity).getUbicacionActual()
                ).toString();

        return envioDTO;
    }

    @Override
    public EnvioDTO consultarDetalleEnvio(int idDetalleEnvio) {
        return null;
    }


}
