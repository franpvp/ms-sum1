package com.duoc.tiendamascotas.services;

import com.duoc.tiendamascotas.dto.DetalleEnvioProductoDTO;
import com.duoc.tiendamascotas.dto.EnvioDTO;
import com.duoc.tiendamascotas.dto.ProductoDTO;
import com.duoc.tiendamascotas.entities.DetalleEnvioProductoEntity;
import com.duoc.tiendamascotas.entities.EnvioEntity;
import com.duoc.tiendamascotas.entities.ProductoEntity;
import com.duoc.tiendamascotas.exceptions.EnvioNotFoundException;
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

    @Override
    public void generarEnvio(EnvioDTO envioDTO) {

        try {
            EnvioEntity envioEntityGuardado = envioRepository.save(EnvioEntity.builder()
                            .ubicacionActual(envioDTO.getUbicacionActual())
                            .destino(envioDTO.getDestino())
                            .idEstadoEnvio(envioDTO.getIdEstadoEnvio())
                            .build());
            envioDTO.getListaProducto().forEach(producto -> {
                DetalleEnvioProductoEntity detalleEnvioProductoEntity = detalleEnvioProductoRepository.save(DetalleEnvioProductoEntity.builder()
                                .idEnvio(envioEntityGuardado.getIdEnvio())
                                .idProducto(producto.getIdProducto())
                        .build());
            });
        } catch (Exception ex) {
            throw new RuntimeException("No se pudo crear el envio");
        }
    }

    @Override
    public void modificarEstadoEnvio(int idEnvio, int idEstadoEnvio) {
        Optional<EnvioEntity> envioOptional = envioRepository.findById(idEnvio);
        if(envioOptional.isPresent()){
            EnvioEntity envioEntity = envioOptional.get();
            envioEntity.setIdEstadoEnvio(idEstadoEnvio);
            envioRepository.save(envioEntity);
        }

    }

    @Override
    public List<EnvioDTO> obtenerEnvios() {
        return envioRepository.findAll().stream().map(envioEntity ->
                consultarEnvioById(envioEntity.getIdEnvio()).get()).toList();
    }

    @Override
    public Optional<EnvioDTO> consultarEnvioById(int id) {

        Optional<EnvioEntity> envio = envioRepository.findById(id);
        if(envio.isPresent()){
            List<DetalleEnvioProductoEntity> listaDetalles = detalleEnvioProductoRepository.findAllByIdEnvio(id);

            List<ProductoDTO> listaProductos = new ArrayList<>();
            listaDetalles.forEach(detalleEnvioProd -> {
                ProductoDTO productoDTO = envioMapper.productoEntityToDTO(productoRepository.findById(detalleEnvioProd.getIdProducto()).get());
                listaProductos.add(productoDTO);
            });

            return Optional.ofNullable(EnvioDTO.builder()
                    .idEnvio(envio.get().getIdEnvio())
                    .listaProducto(listaProductos)
                    .ubicacionActual(envio.get().getUbicacionActual())
                    .destino(envio.get().getDestino())
                    .idEstadoEnvio(envio.get().getIdEstadoEnvio())
                    .build());
        }

        return Optional.empty();
    }

    public String consultarUbicacion(int idEnvio) {
        String envioDTO = envioRepository.findById(idEnvio).stream().map(envioEntity ->
                envioMapper.envioEntityToDTO(envioEntity).getUbicacionActual()
                ).toString();

        return envioDTO;
    }

}
