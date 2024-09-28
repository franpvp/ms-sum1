package com.duoc.citasmedicas.repository;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.dto.MedicoDTO;
import com.duoc.citasmedicas.dto.PacienteDTO;
import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.entities.PacienteEntity;
import com.duoc.citasmedicas.mapper.CitaMapper;
import com.duoc.citasmedicas.repositories.CitaMedicaRepository;
import com.duoc.citasmedicas.repositories.HorarioRepository;
import com.duoc.citasmedicas.repositories.MedicoRepository;
import com.duoc.citasmedicas.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CitaMedicaRepositoryTest {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    @Mock
    private HorarioRepository horarioRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private CitaMapper citaMapper;

    private PacienteEntity pacienteEntity;
    private PacienteDTO pacienteDTO;
    private MedicoEntity medicoEntity;
    private MedicoDTO medicoDTO;
    private HorarioEntity horarioEntity;
    private HorarioDTO horarioDTO;
    private CitaMedicaEntity citaMedicaEntity;
    private CitaMedicaDTO citaMedicaDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        pacienteEntity = PacienteEntity.builder()
                .idPaciente(1)
                .nombre("nombre paciente")
                .idPrevision(1)
                .build();

        pacienteDTO = PacienteDTO.builder()
                .idPaciente(1)
                .idPrevision(1)
                .build();

        medicoEntity = MedicoEntity.builder()
                .idMedico(1)
                .nombre("nombre medico")
                .idEspecialidad(1)
                .build();

        medicoDTO = MedicoDTO.builder()
                .idMedico(1)
                .idEspecialidad(1)
                .build();

        horarioEntity = HorarioEntity.builder()
                .idHorario(1)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        horarioDTO = HorarioDTO.builder()
                .idHorario(1)
                .disponible(true)
                .build();

        citaMedicaEntity = CitaMedicaEntity.builder()
                .pacienteEntity(pacienteEntity)
                .medicoEntity(medicoEntity)
                .horarioEntity(horarioEntity)
                .build();

        citaMedicaDTO = CitaMedicaDTO.builder()
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();
    }

    @Test
    public void crearCitaMedicaTest() {

        CitaMedicaEntity citaMedica = citaMedicaRepository.save(CitaMedicaEntity.builder()
                .pacienteEntity(pacienteEntity)
                .medicoEntity(medicoEntity)
                .horarioEntity(horarioEntity)
                .build());

        // Assert
        assertNotNull(citaMedica.getIdCita(), "El ID de la cita medica no debe ser nulo");
        assertEquals(pacienteEntity, citaMedica.getPaciente());
        assertEquals(medicoEntity, citaMedica.getMedico());
        assertEquals(horarioEntity, citaMedica.getHorario());

    }

    @Test
    public void obtenerCitasMedicasTest() {

        List<CitaMedicaEntity> listaCitas = citaMedicaRepository.findAll();

        assertEquals(5, listaCitas.size());
        assertEquals(1, listaCitas.getFirst().getPaciente().getIdPaciente());
        assertEquals(1, listaCitas.getFirst().getMedico().getIdMedico());
        assertEquals(1, listaCitas.getFirst().getHorario().getIdHorario());

    }

    @Test
    public void obtenerCitaMedicaByIdTest() {

        int idCita = 1;
        Optional<CitaMedicaEntity> citaMedicaEntity1 = citaMedicaRepository.findById(idCita);

        assertEquals(1, citaMedicaEntity1.get().getPaciente().getIdPaciente());
        assertEquals(1, citaMedicaEntity1.get().getMedico().getIdMedico());
        assertEquals(1, citaMedicaEntity1.get().getHorario().getIdHorario());

    }

    @Test
    public void eliminarCitaMedicaByIdTest() {
        int idCita = 1;
        citaMedicaRepository.deleteById(idCita);
        assertFalse(citaMedicaRepository.existsById(idCita));
    }


}
