package com.duoc.citasmedicas.repositories;

import com.duoc.citasmedicas.entities.HorarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends JpaRepository<HorarioEntity, Integer> {
}
