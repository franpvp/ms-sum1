package com.duoc.citasmedicas.controller;

import com.duoc.citasmedicas.controllers.CitaMedicaController;
import com.duoc.citasmedicas.dto.CitaMedicaDTO;
import com.duoc.citasmedicas.dto.HorarioDTO;
import com.duoc.citasmedicas.dto.MedicoDTO;
import com.duoc.citasmedicas.dto.PacienteDTO;
import com.duoc.citasmedicas.services.CitaMedicaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CitaMedicaController.class)
public class CitaMedicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitaMedicaServiceImpl citaMedicaServiceMock;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CitaMedicaController citaMedicaController;

    private PacienteDTO pacienteDTO;
    private MedicoDTO medicoDTO;
    private HorarioDTO horarioDTO;
    private CitaMedicaDTO citaMedicaDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();

        pacienteDTO = PacienteDTO.builder()
                .idPaciente(1)
                .idPrevision(1)
                .build();

        medicoDTO = MedicoDTO.builder()
                .idMedico(1)
                .idEspecialidad(1)
                .build();

        horarioDTO = HorarioDTO.builder()
                .idHorario(1)
                .disponible(true)
                .build();

        citaMedicaDTO = CitaMedicaDTO.builder()
                .idCita(1)
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();
    }

    @Test
    public void getCitasMedicasTest() throws Exception {

        List<CitaMedicaDTO> listaCitasDto = Arrays.asList(citaMedicaDTO);

        when(citaMedicaServiceMock.obtenerCitasMedicas()).thenReturn(listaCitasDto);
        // Ejecutar el test
        mockMvc.perform(get("/api/cita/obtenerCitasMedicas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(citaMedicaServiceMock, times(1)).obtenerCitasMedicas();

    }

    @Test
    public void getCitasByIdTest() throws Exception {

        int idCita = 1;
        when(citaMedicaServiceMock.obtenerCitaMedicaById(idCita)).thenReturn(Optional.ofNullable(any(CitaMedicaDTO.class)));
        // Ejecutar el test
        mockMvc.perform(get("/api/cita/{idCita}", idCita)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(citaMedicaServiceMock, times(1)).obtenerCitaMedicaById(idCita);
    }

    @Test
    public void getHorariosDisponiblesTest() throws Exception {

        List<HorarioDTO> listaHorariosDto = Arrays.asList(horarioDTO);

        when(citaMedicaServiceMock.obtenerDisponibilidadHorarios()).thenReturn(listaHorariosDto);
        // Ejecutar el test
        mockMvc.perform(get("/api/cita/horarios-disponibles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(citaMedicaServiceMock, times(1)).obtenerDisponibilidadHorarios();
    }

//    @Test
//    public void crearCitaMedicaTest() throws Exception {
//
//        when(citaMedicaServiceMock.crearCitaMedica(any(CitaMedicaDTO.class))).thenReturn(citaMedicaDTO);
//
//        mockMvc.perform(post("/api/cita/crearCitaMedica")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(content().string("Cita médica creada correctamente"));
//
//        verify(citaMedicaServiceMock, times(1)).crearCitaMedica(any(CitaMedicaDTO.class));
//    }

    @Test
    public void modificarCitaMedicaTest() throws Exception {

    }

    @Test
    public void eliminarCitaMedicaByIdTest() throws Exception {

    }

}
