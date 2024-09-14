package com.duoc.citasmedicas.entities;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name = "CITAMEDICA")
public class CitaMedicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteEntity pacienteEntity;

    @ManyToOne
    @JoinColumn(name = "id_medico")
    private MedicoEntity medicoEntity;

    @ManyToOne
    @JoinColumn(name = "id_horario")
    private HorarioEntity horarioEntity;

    public CitaMedicaEntity(){

    }

    public CitaMedicaEntity(Integer idCita, PacienteEntity pacienteEntity, MedicoEntity medicoEntity, HorarioEntity horarioEntity) {
        this.idCita = idCita;
        this.pacienteEntity = pacienteEntity;
        this.medicoEntity = medicoEntity;
        this.horarioEntity = horarioEntity;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public PacienteEntity getPaciente() {
        return pacienteEntity;
    }

    public void setPaciente(PacienteEntity pacienteEntity) {
        this.pacienteEntity = pacienteEntity;
    }

    public MedicoEntity getMedico() {
        return medicoEntity;
    }

    public void setMedico(MedicoEntity medicoEntity) {
        this.medicoEntity = medicoEntity;
    }

    public HorarioEntity getHorario() {
        return horarioEntity;
    }

    public void setHorario(HorarioEntity horarioEntity) {
        this.horarioEntity = horarioEntity;
    }

    @Override
    public String toString() {
        return "CitaMedica{" +
                "idCita=" + idCita +
                ", paciente=" + pacienteEntity +
                ", medico=" + medicoEntity +
                ", horario=" + horarioEntity +
                '}';
    }
}
