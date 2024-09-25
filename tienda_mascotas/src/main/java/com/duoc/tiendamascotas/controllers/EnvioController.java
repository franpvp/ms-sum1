package com.duoc.tiendamascotas.controllers;

import com.duoc.tiendamascotas.dto.DetalleEnvioProductoDTO;
import com.duoc.tiendamascotas.dto.EnvioDTO;
import com.duoc.tiendamascotas.services.EnvioProductoService;
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
@RequestMapping("/api/envio")
public class EnvioController {

    @Autowired
    private EnvioProductoService envioProductoService;

    @PostMapping("/generarEnvio")
    public ResponseEntity<String> generarEnvio(
            @Valid @RequestBody EnvioDTO envioDTO
    ) {
        envioProductoService.generarEnvio(envioDTO);
        return new ResponseEntity<>("Envio creado exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/modificarEstado/{idEnvio}/{idEstadoEnvio}")
    public ResponseEntity<String> modificarEstadoEnvio(@PathVariable("idEnvio") int idEnvio, @PathVariable("idEstadoEnvio") int idEstadoEnvio){
        envioProductoService.modificarEstadoEnvio(idEnvio, idEstadoEnvio);

        return new ResponseEntity<>("Estado envio modificado exitosamente", HttpStatus.OK);
    }

    @GetMapping("/obtenerEnvios")
    public ResponseEntity<List<EnvioDTO>> obtenerEnvios() {
        return new ResponseEntity<>(envioProductoService.obtenerEnvios(), HttpStatus.OK);
    }

    @GetMapping("/{idEnvio}")
    public ResponseEntity<Optional<EnvioDTO>> consultarEnvioById(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.consultarEnvioById(idEnvio), HttpStatus.OK);
    }

    @GetMapping("/ubicacion/{idEnvio}")
    public ResponseEntity<String> getUbicacionaActual(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.consultarUbicacion(idEnvio), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idEnvio}")
    public ResponseEntity<String> eliminarEnvioById(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.eliminarEnvio(idEnvio), HttpStatus.OK);
    }
}
