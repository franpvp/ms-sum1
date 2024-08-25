package com.duoc.citasmedicas.services;

import com.duoc.citasmedicas.entities.CitaMedica;
import com.duoc.citasmedicas.entities.Horario;
import com.duoc.citasmedicas.entities.Medico;
import com.duoc.citasmedicas.entities.Paciente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Service
public class CitaMedicaServiceImpl implements CitaMedicaService {

    // Lista pacientes
    List<Paciente> pacientes = new ArrayList<>();
    // Lista medicos
    List<Medico> medicos = new ArrayList<>();
    // Lista de citas médicas
    List<CitaMedica> citas = new ArrayList<>();
    // Lista de horarios disponibles
    List<Horario> horarios = new ArrayList<>();

    private AtomicInteger citaIdCounter = new AtomicInteger(1);

    // Inicializar objetos Paciente y Médicos
    public CitaMedicaServiceImpl() {
        pacientes.add(new Paciente(1, "12345678-9", "Juan", "Pérez", "Fonasa"));
        pacientes.add(new Paciente(2, "98765432-1", "María", "González", "Isapre"));
        pacientes.add(new Paciente(3, "11223344-5", "Carlos", "Rodríguez", "Fonasa"));
        pacientes.add(new Paciente(4, "55667788-0", "Ana", "López", "Particular"));
        pacientes.add(new Paciente(5, "99887766-2", "Luis", "Hernández", "Isapre"));

        medicos.add(new Medico(1, "13579135-9", "Pedro", "Martínez", "Cardiología"));
        medicos.add(new Medico(2, "24682468-1", "Sofía", "Ramírez", "Neurología"));
        medicos.add(new Medico(3, "31415926-5", "Diego", "Fernández", "Pediatría"));
        medicos.add(new Medico(4, "27182818-2", "Laura", "Gómez", "Dermatología"));
        medicos.add(new Medico(5, "16180339-7", "Miguel", "Torres", "Ginecología"));

        // Agregar horarios a una lista
        horarios.add(new Horario(1,LocalDateTime.of(2024,8,10, 6, 30), false));
        horarios.add(new Horario(2, LocalDateTime.of(2024,8,10, 2, 30), false));
        horarios.add(new Horario(3, LocalDateTime.of(2024,8,10, 1, 30), false));
        horarios.add(new Horario(4, LocalDateTime.of(2024,8,10, 7, 30), false));
        horarios.add(new Horario(5, LocalDateTime.of(2024,8,10, 5, 30), false));
        horarios.add(new Horario(6, LocalDateTime.of(2024,8,10, 6, 45), true));
        horarios.add(new Horario(7, LocalDateTime.of(2024,8,10, 8, 30), true));
        horarios.add(new Horario(8, LocalDateTime.of(2024,8,10, 10, 30), true));

        //Crear citas médicas
        citas.add(new CitaMedica(citaIdCounter.getAndIncrement(), pacientes.get(0), medicos.get(0), horarios.get(0)));
        citas.add(new CitaMedica(citaIdCounter.getAndIncrement(), pacientes.get(1), medicos.get(1), horarios.get(1)));
        citas.add(new CitaMedica(citaIdCounter.getAndIncrement(), pacientes.get(2), medicos.get(2), horarios.get(2)));
        citas.add(new CitaMedica(citaIdCounter.getAndIncrement(), pacientes.get(3), medicos.get(3), horarios.get(3)));
        citas.add(new CitaMedica(citaIdCounter.getAndIncrement(), pacientes.get(4), medicos.get(4), horarios.get(4)));

    }

    @Override
    public CitaMedica obtenerCitaMedica(int id) {
        return citas.stream()
                .filter(c -> c.getIdCita() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cita Médica no encontrada"));
    }

    @Override
    public List<Horario> obtenerDisponibilidadHorarios() {
        return horarios;
    }
}
