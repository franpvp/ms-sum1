package com.duoc.tiendamascotas.repository;

import com.duoc.tiendamascotas.repositories.DetalleEnvioRepository;
import com.duoc.tiendamascotas.repositories.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class DetalleEnvioRepositoryTest {

    @Autowired
    private DetalleEnvioRepository detalleEnvioRepository;

    @Test
    public void findAllByIdEnvioTest() {

    }
}
