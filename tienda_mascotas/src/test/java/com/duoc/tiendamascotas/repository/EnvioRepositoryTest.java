package com.duoc.tiendamascotas.repository;

import com.duoc.tiendamascotas.repositories.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository envioRepository;

    @Test
    public void generarEnvioTest() {

    }

    @Test
    public void actualizarEnvioByIdTest() {

    }

    @Test
    public void obtenerEnviosTest() {

    }

    @Test
    public void obtenerEnvioByIdTest() {

    }

    @Test
    public void obtenerUbicacionTest() {

    }

    @Test
    public void eliminarEnvioByIdTest() {

    }

}
