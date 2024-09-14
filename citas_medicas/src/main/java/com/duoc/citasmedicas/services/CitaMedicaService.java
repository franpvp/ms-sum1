package com.duoc.citasmedicas.services;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import com.duoc.citasmedicas.entities.HorarioEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CitaMedicaService {

    Optional<CitaMedicaDTO> obtenerCitaMedica(int id);

    List<HorarioDTO> obtenerDisponibilidadHorarios();

    void crearCitaMedica(CitaMedicaDTO citaMedicaDTO);

    CitaMedicaDTO modificarCitaMedica(int id_cita, CitaMedicaDTO citaMedicaDTO);

    void eliminarCitaMedicaById(int id_cita);

}
