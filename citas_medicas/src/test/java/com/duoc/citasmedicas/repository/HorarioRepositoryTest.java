package com.duoc.citasmedicas.repository;

import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.repositories.HorarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioRepositoryTest {

    @Autowired
    private HorarioRepository horarioRepository;

    @Test
    public void obtenerHorariosDisponiblesTest() {

        List<HorarioEntity> listaHorarios = horarioRepository.findAll();

        assertEquals(7, listaHorarios.size());
        assertEquals(1, listaHorarios.getFirst().getIdHorario());
        assertEquals(false, listaHorarios.getFirst().isDisponible());
    }
}
