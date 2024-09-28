package com.duoc.citasmedicas.repository;

import com.duoc.citasmedicas.entities.PacienteEntity;
import com.duoc.citasmedicas.repositories.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    public void obtenerPacientesTest() {

        List<PacienteEntity> listaPacientes = pacienteRepository.findAll();

        assertEquals(5, listaPacientes.size());
        assertEquals("Juan", listaPacientes.getFirst().getNombre());
        assertEquals("Pérez", listaPacientes.getFirst().getApellidoPaterno());
        assertEquals("Pérez", listaPacientes.getFirst().getApellidoMaterno());
        assertEquals(1, listaPacientes.getFirst().getIdPrevision());


    }
}
