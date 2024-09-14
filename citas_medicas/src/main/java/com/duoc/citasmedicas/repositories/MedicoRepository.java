package com.duoc.citasmedicas.repositories;

import com.duoc.citasmedicas.entities.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Integer> {
}
