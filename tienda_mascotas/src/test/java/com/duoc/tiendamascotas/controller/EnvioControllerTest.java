package com.duoc.tiendamascotas.controller;

import com.duoc.tiendamascotas.controllers.EnvioController;
import com.duoc.tiendamascotas.dto.*;
import com.duoc.tiendamascotas.entities.DetalleEnvioProductoEntity;
import com.duoc.tiendamascotas.entities.EnvioEntity;
import com.duoc.tiendamascotas.entities.ProductoEntity;
import com.duoc.tiendamascotas.services.EnvioProductoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(EnvioController.class)
public class EnvioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioProductoServiceImpl envioProductoService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private EnvioController envioController;

    private ProductoDTO productoDTO;

    private EnvioDTO envioDTO;

    private DetalleEnvioProductoDTO detalleEnvioProductoDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void generarEnvioTest() throws Exception {

        // Simulación del servicio
        when(envioProductoService.generarEnvio(any(EnvioDTO.class))).thenReturn(envioDTO);
        // Ejecutar el test
        mockMvc.perform(post("/api/envio/generarEnvio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"ubicacionActual\": \"ubicación 1\", \"destino\": \"destino 1\", \"idEstadoEnvio\": 1}")
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(content().string("Envio creado exitosamente"));

        verify(envioProductoService, times(1)).generarEnvio(any(EnvioDTO.class));

    }

    @Test
    public void modificarEstadoEnvioTest() throws Exception {
        int idEnvio = 1;
        int idEstadoEnvio = 2;
        // Simulación del servicio
        doNothing().when(envioProductoService).modificarEstadoEnvio(idEnvio, idEstadoEnvio);

        // Ejecutar el test
        mockMvc.perform(put("/api/envio/modificarEstado/{idEnvio}/{idEstadoEnvio}", idEnvio, idEstadoEnvio))
                .andExpect(status().isOk())
                .andExpect(content().string("Estado envio modificado exitosamente"));

        verify(envioProductoService, times(1)).modificarEstadoEnvio(idEnvio, idEstadoEnvio);

    }

    @Test
    public void obtenerEnviosTest() throws Exception {
        EnvioDTO envioDTO1 = EnvioDTO.builder()
                .ubicacionActual("ubicación 1")
                .destino("destino 1")
                .idEstadoEnvio(1)
                .build();

        EnvioDTO envioDTO2 = EnvioDTO.builder()
                .ubicacionActual("ubicación 2")
                .destino("destino 2")
                .idEstadoEnvio(1)
                .build();

        List<EnvioDTO> listaDtos = Arrays.asList(envioDTO1, envioDTO2);

        // Simular el servicio
        when(envioProductoService.obtenerEnvios()).thenReturn(listaDtos);

        mockMvc.perform(get("/api/envio/obtenerEnvios")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ubicacionActual").value("ubicación 1"))
                .andExpect(jsonPath("$[1].ubicacionActual").value("ubicación 2"));

        // Verificar que se llamó al servicio una vez
        verify(envioProductoService, times(1)).obtenerEnvios();
    }

    @Test
    public void consultarEnvioByIdTest() throws Exception {

        int idEnvio = 1;
        EnvioDTO envioDTO1 = EnvioDTO.builder()
                .idEnvio(1)
                .ubicacionActual("ubicación 1")
                .destino("destino 1")
                .idEstadoEnvio(1)
                .build();

        when(envioProductoService.consultarEnvioById(anyInt())).thenReturn(Optional.ofNullable(envioDTO1));

        mockMvc.perform(get("/api/envio/{idEnvio}", idEnvio)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idEnvio").value(1));

        verify(envioProductoService, times(1)).consultarEnvioById(idEnvio);

    }

    @Test
    public void getUbicacionActualTest() throws Exception {

        int idEnvio = 1;
        UbicacionActualDTO ubicacionActualDTO = new UbicacionActualDTO("Ubicación Actual");
        when(envioProductoService.consultarUbicacion(anyInt())).thenReturn(String.valueOf(ubicacionActualDTO));

        mockMvc.perform(get("/api/envio/ubicacion/{idEnvio}", idEnvio))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ubicacion").value("Ubicación Actual"));

        verify(envioProductoService, times(1)).consultarUbicacion(idEnvio);

    }

    @Test
    public void eliminarEnvioByIdTest() throws Exception {
        int idEnvio = 1;
        EnvioEliminadoDTO envioEliminadoDTO = new EnvioEliminadoDTO("Envio eliminado exitosamente");

        when(envioProductoService.eliminarEnvio(anyInt())).thenReturn(String.valueOf(envioEliminadoDTO));

        mockMvc.perform(delete("/api/envio/eliminar/{idEnvio}", idEnvio))
                .andExpect(status().isOk());

        verify(envioProductoService, times(1)).eliminarEnvio(idEnvio);
    }


}
