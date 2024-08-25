package com.duoc.citasmedicas.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CitaMedica {

    // Consultar citas y consultar la disponibilidad de horarios.
    private int idCita;
    private Paciente paciente;
    private Medico medico;
    private Horario horario;

    public CitaMedica(int idCita, Paciente paciente, Medico medico, Horario horario) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.horario = horario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
