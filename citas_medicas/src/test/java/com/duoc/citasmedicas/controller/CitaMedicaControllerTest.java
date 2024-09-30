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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        // Simular una lista de citas médicas
        CitaMedicaDTO citaMedicaDTO1 = CitaMedicaDTO.builder()
                .idCita(1)
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();

        CitaMedicaDTO citaMedicaDTO2 = CitaMedicaDTO.builder()
                .idCita(2)
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();

        List<CitaMedicaDTO> listaCitasDto = Arrays.asList(citaMedicaDTO1, citaMedicaDTO2);

        // Simular el servicio
        when(citaMedicaServiceMock.obtenerCitasMedicas()).thenReturn(listaCitasDto);

        // Ejecutar el test
        mockMvc.perform(get("/api/cita/obtener-citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._embedded.citaMedicaDTOList[0]._links.self.href").exists())
                .andExpect(jsonPath("$._embedded.citaMedicaDTOList[0]._links.eliminarCita.href").exists())
                .andExpect(jsonPath("$._embedded.citaMedicaDTOList[1]._links.self.href").exists())
                .andExpect(jsonPath("$._embedded.citaMedicaDTOList[1]._links.eliminarCita.href").exists());

        verify(citaMedicaServiceMock, times(1)).obtenerCitasMedicas();
    }

    @Test
    public void getCitasByIdTest() throws Exception {

        int idCita = 1;
        when(citaMedicaServiceMock.obtenerCitaMedicaById(idCita)).thenReturn(citaMedicaDTO);

        mockMvc.perform(get("/api/cita/{id-cita}", idCita)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCita").value(citaMedicaDTO.getIdCita()))
                .andExpect(jsonPath("$.idCita").value(citaMedicaDTO.getIdCita()))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.eliminarCita.href").exists())
                .andExpect(jsonPath("$._links.allCitas.href").exists());

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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.citas.href").exists())
                .andExpect(jsonPath("$._embedded.horarioDTOList[0]._links.self.href").exists());

        verify(citaMedicaServiceMock, times(1)).obtenerDisponibilidadHorarios();
    }

    @Test
    public void crearCitaMedicaTest() throws Exception {

        when(citaMedicaServiceMock.crearCitaMedica(any(CitaMedicaDTO.class))).thenReturn(citaMedicaDTO);

        mockMvc.perform(post("/api/cita")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citaMedicaDTO))) // Aquí se agrega el contenido
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.allCitas.href").exists());

        verify(citaMedicaServiceMock, times(1)).crearCitaMedica(any(CitaMedicaDTO.class));
    }

    @Test
    public void modificarCitaMedicaTest() throws Exception {

        int idCita = 1;
        citaMedicaDTO.setIdCita(idCita);
        CitaMedicaDTO citaMedicaDTOActualizada = CitaMedicaDTO.builder()
                .pacienteDTO(pacienteDTO)
                .medicoDTO(medicoDTO)
                .horarioDTO(horarioDTO)
                .build();

        citaMedicaDTOActualizada.setIdCita(idCita);
        when(citaMedicaServiceMock.modificarCitaMedica(idCita, citaMedicaDTO)).thenReturn(citaMedicaDTOActualizada);

        mockMvc.perform(put("/api/cita/modificar-cita/{id-cita}", idCita)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citaMedicaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCita").value(idCita))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.eliminarCita.href").exists())
                .andExpect(jsonPath("$._links.allCitas.href").exists());
    }

    @Test
    public void eliminarCitaMedicaByIdTest() throws Exception {

        int idCita = 1;
        doNothing().when(citaMedicaServiceMock).eliminarCitaMedicaById(idCita);

        mockMvc.perform(delete("/api/cita/borrar-cita/{id-cita}", idCita)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.cita.href").exists())
                .andExpect(jsonPath("$._links.allCitas.href").exists());

        verify(citaMedicaServiceMock, times(1)).eliminarCitaMedicaById(idCita);
    }

}
