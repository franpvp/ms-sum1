package com.duoc.tiendamascotas.repositories;

import com.duoc.tiendamascotas.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
