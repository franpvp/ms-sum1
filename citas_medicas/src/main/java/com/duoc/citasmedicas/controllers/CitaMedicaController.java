package com.duoc.citasmedicas.controllers;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.services.CitaMedicaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/cita")
public class CitaMedicaController {
    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping("/obtenerCitasMedicas")
    public ResponseEntity<List<CitaMedicaDTO>> getCitasMedicas() {
        return new ResponseEntity<>(citaMedicaService.obtenerCitasMedicas(), HttpStatus.OK);
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<Optional<CitaMedicaDTO>> getCitasById(@PathVariable("idCita") int idCita) {
        return new ResponseEntity<>(citaMedicaService.obtenerCitaMedicaById(idCita), HttpStatus.OK);
    }

    @GetMapping("/horarios-disponibles")
    public ResponseEntity<List<HorarioDTO>> getHorariosDisponibles() {
        return new ResponseEntity<>(citaMedicaService.obtenerDisponibilidadHorarios(), HttpStatus.OK);
    }

    @PostMapping("/crearCitaMedica")
    public ResponseEntity<String> crearCitaMedica(
            @Valid @RequestBody(required = true) CitaMedicaDTO citaMedicaDTO
    ) {
        citaMedicaService.crearCitaMedica(citaMedicaDTO);
        return new ResponseEntity<>("Cita médica creada correctamente", HttpStatus.CREATED);
    }

    @PutMapping("modificarCitaMedica/{id_cita}")
    public ResponseEntity<CitaMedicaDTO> modificarCitaMedica(
            @PathVariable("id_cita") int id_cita,
            @Valid @RequestBody(required = true) CitaMedicaDTO nuevaCitaMedicaDTO
    ) {
        CitaMedicaDTO citaMedicaDTOActualizada = citaMedicaService.modificarCitaMedica(id_cita, nuevaCitaMedicaDTO);
        if (citaMedicaDTOActualizada != null) {
            return ResponseEntity.ok(citaMedicaDTOActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("borrarCitaMedica/{id_cita}")
    public String eliminarCitaMedicaById(
            @PathVariable("id_cita") int id_cita
    ) {
        citaMedicaService.eliminarCitaMedicaById(id_cita);
        return "Cita Eliminada Exitosamente";
    }

}
