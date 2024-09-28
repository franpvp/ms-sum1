package com.duoc.citasmedicas.service;

import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.dto.MedicoDTO;
import com.duoc.citasmedicas.dto.PacienteDTO;
import com.duoc.citasmedicas.entities.CitaMedicaEntity;
import com.duoc.citasmedicas.entities.HorarioEntity;
import com.duoc.citasmedicas.entities.MedicoEntity;
import com.duoc.citasmedicas.entities.PacienteEntity;
import com.duoc.citasmedicas.exceptions.CitaMedicaNotFoundException;
import com.duoc.citasmedicas.exceptions.HorarioNotAvailableException;
import com.duoc.citasmedicas.exceptions.IllegalNumberException;
import com.duoc.citasmedicas.mapper.CitaMapper;
import com.duoc.citasmedicas.repositories.CitaMedicaRepository;
import com.duoc.citasmedicas.repositories.HorarioRepository;
import com.duoc.citasmedicas.repositories.MedicoRepository;
import com.duoc.citasmedicas.repositories.PacienteRepository;
import com.duoc.citasmedicas.services.CitaMedicaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @Mock
    private HorarioRepository horarioRepository;

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private MedicoRepository medicoRepository;

    @Mock
    private CitaMapper citaMapper;

    @InjectMocks
    private CitaMedicaServiceImpl citaMedicaServiceMock;

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
                .nombre("nombre")
                .apellidoPaterno("apellido paterno")
                .apellidoMaterno("apellido materno")
                .idPrevision(1)
                .build();

        pacienteDTO = PacienteDTO.builder()
                .idPaciente(1)
                .nombre("nombre")
                .apellidoPaterno("apellido paterno")
                .apellidoMaterno("apellido materno")
                .idPrevision(1)
                .build();

        medicoEntity = MedicoEntity.builder()
                .idMedico(1)
                .nombre("nombre")
                .apellidoPaterno("apellido paterno")
                .apellidoMaterno("apellido materno")
                .idEspecialidad(1)
                .build();

        medicoDTO = MedicoDTO.builder()
                .idMedico(1)
                .nombre("nombre")
                .apellidoPaterno("apellido paterno")
                .apellidoMaterno("apellido materno")
                .idEspecialidad(1)
                .build();

        horarioEntity = HorarioEntity.builder()
                .idHorario(1)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        horarioDTO = HorarioDTO.builder()
                .idHorario(1)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        citaMedicaEntity = CitaMedicaEntity.builder()
                .idCita(1)
                .pacienteEntity(pacienteEntity)
                .medicoEntity(medicoEntity)
                .horarioEntity(horarioEntity)
                .build();

        citaMedicaDTO = CitaMedicaDTO.builder()
                .idCita(1)
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();
    }

    @Test
    public void obtenerCitasMedicasTest() {

        List<CitaMedicaEntity> listaCitas = Arrays.asList(citaMedicaEntity);

        when(citaMedicaRepository.findAll()).thenReturn(listaCitas);
        when(citaMapper.citaEntityToDTO(citaMedicaEntity)).thenReturn(citaMedicaDTO);

        List<CitaMedicaDTO> resultado = citaMedicaServiceMock.obtenerCitasMedicas();

        assertEquals(1, resultado.size());
        assertEquals(citaMedicaDTO, resultado.get(0));

        verify(citaMedicaRepository).findAll();
    }

    @Test
    public void obtenerCitasMedicasVacioTest() {
        // Simulación de repositorio vacío
        when(citaMedicaRepository.findAll()).thenReturn(new ArrayList<>());

        // Verificar que se lanza la excepción correcta
        assertThrows(CitaMedicaNotFoundException.class, () -> {
            citaMedicaServiceMock.obtenerCitasMedicas();
        });
        verify(citaMedicaRepository).findAll();
    }

    @Test
    public void obtenerCitaMedicaByIdTest() {

        when(citaMedicaRepository.findById(1)).thenReturn(Optional.ofNullable(citaMedicaEntity));
        when(citaMapper.citaEntityToDTO(citaMedicaEntity)).thenReturn(citaMedicaDTO);

        CitaMedicaDTO resultado = citaMedicaServiceMock.obtenerCitaMedicaById(1);

        assertEquals(pacienteDTO, resultado.getPacienteDTO());
        assertEquals(medicoDTO, resultado.getMedicoDTO());
        assertEquals(horarioDTO, resultado.getHorarioDTO());

        verify(citaMedicaRepository).findById(1);
    }

    @Test
    public void obtenerCitaMedicaByIdNoEncontradaTest() {
        int idCita = 1;
        // Simulación de que el repositorio no encuentra la cita médica
        when(citaMedicaRepository.findById(idCita)).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción cuando la cita no se encuentra
        CitaMedicaNotFoundException exception = assertThrows(CitaMedicaNotFoundException.class, () -> {
            citaMedicaServiceMock.obtenerCitaMedicaById(idCita);
        });

        assertEquals("Cita médica no encontrada con id: " + idCita, exception.getMessage());

        verify(citaMedicaRepository).findById(1);
    }

    @Test
    public void obtenerCitaMedicaByIdInvalidoTest() {
        // Verificar que se lanza la excepción cuando el id es negativo o cero
        IllegalNumberException exception = assertThrows(IllegalNumberException.class, () -> {
            citaMedicaServiceMock.obtenerCitaMedicaById(0);
        });

        assertEquals("El ID del envío debe ser positivo y no nulo", exception.getMessage());
    }

    @Test
    public void obtenerDisponibilidadHorariosTest() {

        HorarioEntity horarioEntity1 = HorarioEntity.builder()
                .idHorario(1)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        HorarioEntity horarioEntity2 = HorarioEntity.builder()
                .idHorario(2)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        HorarioDTO horarioDTO1 = HorarioDTO.builder()
                .idHorario(1)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        HorarioDTO horarioDTO2 = HorarioDTO.builder()
                .idHorario(2)
                .fechaHora(LocalDateTime.now())
                .disponible(true)
                .build();

        List<HorarioEntity> listaHorarioDto = Arrays.asList(horarioEntity1, horarioEntity2);

        when(horarioRepository.findAll()).thenReturn(listaHorarioDto);
        when(citaMapper.horarioEntityToDTO(horarioEntity1)).thenReturn(horarioDTO1);
        when(citaMapper.horarioEntityToDTO(horarioEntity2)).thenReturn(horarioDTO2);

        List<HorarioDTO> resultado = citaMedicaServiceMock.obtenerDisponibilidadHorarios();

        assertEquals(2, resultado.size());
        assertEquals(horarioDTO1, resultado.get(0));
        assertEquals(horarioDTO2, resultado.get(1));

        verify(horarioRepository).findAll();
    }

    @Test
    public void crearCitaMedicaTest() {

        when(pacienteRepository.findById(1)).thenReturn(Optional.of(pacienteEntity));
        when(medicoRepository.findById(1)).thenReturn(Optional.of(medicoEntity));
        when(horarioRepository.findById(1)).thenReturn(Optional.of(horarioEntity));
        when(citaMedicaRepository.save(any(CitaMedicaEntity.class))).thenReturn(citaMedicaEntity);
        when(citaMapper.citaEntityToDTO(any(CitaMedicaEntity.class))).thenReturn(citaMedicaDTO);

        // Ejecutar el método
        CitaMedicaDTO resultado = citaMedicaServiceMock.crearCitaMedica(citaMedicaDTO);

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(citaMedicaDTO, resultado);

        // Verificar interacciones
        verify(pacienteRepository).findById(1);
        verify(medicoRepository).findById(1);
        verify(horarioRepository).findById(1);
        verify(citaMedicaRepository).save(any(CitaMedicaEntity.class));
        verify(horarioRepository).save(horarioEntity);

    }

    @Test
    public void crearCitaMedicaHorarioNoDisponibleTest() {

        HorarioEntity horarioEntity = HorarioEntity.builder()
                .idHorario(1)
                .disponible(false)
                .build();

        HorarioDTO horarioDTO = HorarioDTO.builder()
                .idHorario(1)
                .disponible(false)
                .build();

        CitaMedicaDTO citaMedicaDTO = CitaMedicaDTO.builder()
                .idCita(1)
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();

        when(pacienteRepository.findById(1)).thenReturn(Optional.ofNullable(pacienteEntity));
        when(medicoRepository.findById(1)).thenReturn(Optional.ofNullable(medicoEntity));
        when(horarioRepository.findById(1)).thenReturn(Optional.ofNullable(horarioEntity));

        // Ejecutar y verificar excepción
        HorarioNotAvailableException exception = assertThrows(HorarioNotAvailableException.class, () -> {
            citaMedicaServiceMock.crearCitaMedica(citaMedicaDTO);
        });

        assertEquals("Horario ingresado no disponible: " + horarioEntity, exception.getMessage());
    }

    @Test
    public void modificarCitaMedicaTest() {
        // Simulación de los repositorios
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(pacienteEntity));
        when(medicoRepository.findById(1)).thenReturn(Optional.of(medicoEntity));
        when(horarioRepository.findById(1)).thenReturn(Optional.of(horarioEntity));
        when(citaMedicaRepository.findById(1)).thenReturn(Optional.of(citaMedicaEntity));
        when(citaMedicaRepository.save(any(CitaMedicaEntity.class))).thenReturn(citaMedicaEntity);
        when(citaMapper.citaEntityToDTO(any(CitaMedicaEntity.class))).thenReturn(citaMedicaDTO);

        // Ejecutar el método
        CitaMedicaDTO resultado = citaMedicaServiceMock.modificarCitaMedica(1, citaMedicaDTO);

        assertNotNull(resultado);
        assertEquals(citaMedicaDTO, resultado);

        // Verificar interacciones
        verify(pacienteRepository).findById(1);
        verify(medicoRepository).findById(1);
        verify(horarioRepository).findById(1);
        verify(citaMedicaRepository).findById(1);
        verify(citaMedicaRepository).save(any(CitaMedicaEntity.class));

    }

    @Test
    public void modificarCitaMedicaCitaNoEncontradaTest() {
        // Simular que la cita médica no existe
        when(pacienteRepository.findById(1)).thenReturn(Optional.of(pacienteEntity));
        when(medicoRepository.findById(1)).thenReturn(Optional.of(medicoEntity));
        when(horarioRepository.findById(1)).thenReturn(Optional.of(horarioEntity));
        when(citaMedicaRepository.findById(1)).thenReturn(Optional.empty());

        // Ejecutar y verificar excepción
        CitaMedicaNotFoundException exception = assertThrows(CitaMedicaNotFoundException.class, () -> {
            citaMedicaServiceMock.modificarCitaMedica(1, citaMedicaDTO);
        });

        assertEquals("Cita médica no encontrada con id: 1", exception.getMessage());
    }


    @Test
    public void eliminarCitaMedicaByIdTest() {
        // Simular la cita médica encontrada
        when(citaMedicaRepository.findById(1)).thenReturn(Optional.of(citaMedicaEntity));

        // Ejecutar el método
        citaMedicaServiceMock.eliminarCitaMedicaById(1);

        // Verificar que el horario se ha marcado como disponible y se ha guardado
        assertTrue(horarioEntity.isDisponible());
        verify(horarioRepository).save(horarioEntity);

        // Verificar que la cita médica ha sido eliminada
        verify(citaMedicaRepository).deleteById(1);
    }

    @Test
    public void eliminarCitaMedicaByIdCitaNoEncontradaTest() {
        // Simular que la cita médica no existe
        when(citaMedicaRepository.findById(1)).thenReturn(Optional.empty());

        // Ejecutar y verificar excepción
        CitaMedicaNotFoundException exception = assertThrows(CitaMedicaNotFoundException.class, () -> {
            citaMedicaServiceMock.eliminarCitaMedicaById(1);
        });

        assertEquals("Cita médica no encontrada con id: 1", exception.getMessage());
    }


}
