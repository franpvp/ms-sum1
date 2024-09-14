package com.duoc.citasmedicas.repositories;

import com.duoc.citasmedicas.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {
}
