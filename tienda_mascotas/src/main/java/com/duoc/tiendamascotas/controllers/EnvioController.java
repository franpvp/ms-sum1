package com.duoc.tiendamascotas.controllers;

import com.duoc.tiendamascotas.entities.Envio;
import com.duoc.tiendamascotas.services.EnvioProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/envio")
public class EnvioController {

    @Autowired
    private EnvioProductoService envioProductoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Envio>> obtenerEnvios() {
        return new ResponseEntity<>(envioProductoService.obtenerEnvios(), HttpStatus.CREATED);
    }

    @GetMapping("/{idEnvio}")
    public ResponseEntity<Envio> getEnvioById(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.consultarEnvioById(idEnvio), HttpStatus.CREATED);
    }

    @GetMapping("/ubicacion/{idEnvio}")
    public ResponseEntity<String> getUbicacionActual(@PathVariable("idEnvio") int idEnvio){
        return new ResponseEntity<>(envioProductoService.consultarUbicacion(idEnvio), HttpStatus.CREATED);
    }
}
