package com.duoc.tiendamascotas.repositories;

import com.duoc.tiendamascotas.entities.DetalleEnvioProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleEnvioProductoRepository extends JpaRepository<DetalleEnvioProductoEntity, Integer> {

    List<DetalleEnvioProductoEntity> findAllByIdEnvio(int id);
}
