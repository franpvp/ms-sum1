package com.duoc.citasmedicas.repository;

import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.repositories.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Test
    public void obtenerMedicosTest() {

        List<MedicoEntity> listaMedicos = medicoRepository.findAll();

        assertEquals(5, listaMedicos.size());
        assertEquals("Pedro", listaMedicos.getFirst().getNombre());
        assertEquals("Martínez", listaMedicos.getFirst().getApellidoPaterno());
        assertEquals("Martínez", listaMedicos.getFirst().getApellidoMaterno());
        assertEquals(1, listaMedicos.getFirst().getIdEspecialidad());

    }
}
