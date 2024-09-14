package com.duoc.citasmedicas.repositories;

import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaMedicaRepository extends JpaRepository<CitaMedicaEntity, Integer> {
}
