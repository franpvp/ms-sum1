package com.duoc.citasmedicas.controllers;

import com.duoc.citasmedicas.entities.CitaMedica;
import com.duoc.citasmedicas.entities.Horario;
import com.duoc.citasmedicas.services.CitaMedicaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cita")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping("/{idCita}")
    public ResponseEntity<CitaMedica> getCitasById(@PathVariable("idCita") int idCita) {
        return new ResponseEntity<>(citaMedicaService.obtenerCitaMedica(idCita), HttpStatus.CREATED);
    }

    @GetMapping("/horarios-disponibles")
    public ResponseEntity<List<Horario>> getHorariosDisponibles() {
        return new ResponseEntity<>(citaMedicaService.obtenerDisponibilidadHorarios(), HttpStatus.CREATED);
    }


}
