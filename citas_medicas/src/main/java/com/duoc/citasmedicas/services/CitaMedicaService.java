package com.duoc.citasmedicas.services;

import com.duoc.citasmedicas.entities.CitaMedica;
import com.duoc.citasmedicas.entities.Horario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CitaMedicaService {

    CitaMedica obtenerCitaMedica(int id);
    List<Horario> obtenerDisponibilidadHorarios();
}
