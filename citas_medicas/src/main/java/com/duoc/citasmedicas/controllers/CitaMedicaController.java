package com.duoc.citasmedicas.controllers;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.EliminarCitaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.services.CitaMedicaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/api/cita")
public class CitaMedicaController {
    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping("/obtener-citas")
    public ResponseEntity<CollectionModel<EntityModel<CitaMedicaDTO>>> obtenerCitasMedicas() {
        List<CitaMedicaDTO> citas = citaMedicaService.obtenerCitasMedicas();

        List<EntityModel<CitaMedicaDTO>> citaModels = citas.stream()
                .map(cita -> EntityModel.of(cita,
                        linkTo(methodOn(CitaMedicaController.class).getCitasById(cita.getIdCita())).withSelfRel(),
                        linkTo(methodOn(CitaMedicaController.class).eliminarCitaMedicaById(cita.getIdCita())).withRel("eliminarCita")
                ))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<CitaMedicaDTO>> collectionModel = CollectionModel.of(citaModels,
                linkTo(methodOn(CitaMedicaController.class).obtenerCitasMedicas()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id-cita}")
    public ResponseEntity<EntityModel<CitaMedicaDTO>> getCitasById(@PathVariable("id-cita") int idCita) {
        CitaMedicaDTO cita = citaMedicaService.obtenerCitaMedicaById(idCita);

        EntityModel<CitaMedicaDTO> citaModel = EntityModel.of(cita,
                linkTo(methodOn(CitaMedicaController.class).getCitasById(idCita)).withSelfRel(),
                linkTo(methodOn(CitaMedicaController.class).eliminarCitaMedicaById(idCita)).withRel("eliminarCita"),
                linkTo(methodOn(CitaMedicaController.class).obtenerCitasMedicas()).withRel("allCitas"));

        return new ResponseEntity<>(citaModel, HttpStatus.OK);
    }

    @GetMapping("/horarios-disponibles")
    public ResponseEntity<CollectionModel<EntityModel<HorarioDTO>>> getHorariosDisponibles() {
        List<HorarioDTO> horarios = citaMedicaService.obtenerDisponibilidadHorarios();

        List<EntityModel<HorarioDTO>> horarioModels = horarios.stream()
                .map(horario -> EntityModel.of(horario,
                        linkTo(methodOn(CitaMedicaController.class).getCitasById(horario.getIdHorario())).withSelfRel()
                ))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<HorarioDTO>> collectionModel = CollectionModel.of(horarioModels,
                linkTo(methodOn(CitaMedicaController.class).obtenerCitasMedicas()).withRel("citas"),
                linkTo(methodOn(CitaMedicaController.class).getHorariosDisponibles()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @PutMapping("/modificar-cita/{id-cita}")
    public ResponseEntity<EntityModel<CitaMedicaDTO>> modificarEstadoEnvio(
            @PathVariable("id-cita") int idCita,
            @Valid @RequestBody(required = true) CitaMedicaDTO nuevaCitaMedicaDTO
    ) {
        CitaMedicaDTO citaMedicaDTOActualizada = citaMedicaService.modificarCitaMedica(idCita, nuevaCitaMedicaDTO);

        // Crear EntityModel con los enlaces
        EntityModel<CitaMedicaDTO> responseModel = EntityModel.of(citaMedicaDTOActualizada,
                linkTo(methodOn(CitaMedicaController.class).getCitasById(citaMedicaDTOActualizada.getIdCita())).withSelfRel(),
                linkTo(methodOn(CitaMedicaController.class).eliminarCitaMedicaById(idCita)).withRel("eliminarCita"),
                linkTo(methodOn(CitaMedicaController.class).obtenerCitasMedicas()).withRel("allCitas"));
        // Devolver la respuesta con el EntityModel y el status OK
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<EntityModel<CitaMedicaDTO>> crearCitaMedica(
            @Valid @RequestBody CitaMedicaDTO citaMedicaDTO
    ) {
        CitaMedicaDTO citaCreada = citaMedicaService.crearCitaMedica(citaMedicaDTO);

        EntityModel<CitaMedicaDTO> citaModel = EntityModel.of(citaCreada,
                linkTo(methodOn(CitaMedicaController.class).getCitasById(citaCreada.getIdCita())).withSelfRel(),
                linkTo(methodOn(CitaMedicaController.class).eliminarCitaMedicaById(citaCreada.getIdCita())).withRel("eliminarCita"),
                linkTo(methodOn(CitaMedicaController.class).obtenerCitasMedicas()).withRel("allCitas"));

        return new ResponseEntity<>(citaModel, HttpStatus.CREATED);

    }

    @DeleteMapping("/borrar-cita/{id-cita}")
    public ResponseEntity<EntityModel<EliminarCitaDTO>> eliminarCitaMedicaById(@PathVariable("id-cita") int id_cita) {

        citaMedicaService.eliminarCitaMedicaById(id_cita);
        EliminarCitaDTO envioEliminadoDTO = new EliminarCitaDTO("Cita Medica eliminada con id: " + id_cita);

        WebMvcLinkBuilder linkToEnvio = linkTo(methodOn(CitaMedicaController.class).getCitasById(id_cita));
        WebMvcLinkBuilder linkToAllEnvios = linkTo(methodOn(CitaMedicaController.class).obtenerCitasMedicas());

        EntityModel<EliminarCitaDTO> responseModel = EntityModel.of(envioEliminadoDTO,
                linkToEnvio.withRel("cita"),
                linkToAllEnvios.withRel("allCitas"));

        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

}
