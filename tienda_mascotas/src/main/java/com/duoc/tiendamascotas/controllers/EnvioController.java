package com.duoc.tiendamascotas.controllers;

import com.duoc.tiendamascotas.dto.EnvioDTO;
import com.duoc.tiendamascotas.services.EnvioProductoService;
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
            @RequestBody EnvioDTO envioDTO
    ) {
        envioProductoService.generarEnvio(envioDTO);
        return new ResponseEntity<>("Envio creado exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/getLista")
    public ResponseEntity<List<EnvioDTO>> obtenerEnvios() {
        return new ResponseEntity<>(envioProductoService.obtenerEnvios(), HttpStatus.CREATED);
    }

    @GetMapping("/{idEnvio}")
    public ResponseEntity<Optional<EnvioDTO>> getEnvioById(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.consultarEnvioById(idEnvio), HttpStatus.CREATED);
    }

    @GetMapping("/ubicacion/{idEnvio}")
    public ResponseEntity<String> getUbicacionActual(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.consultarUbicacion(idEnvio), HttpStatus.CREATED);
    }
}
