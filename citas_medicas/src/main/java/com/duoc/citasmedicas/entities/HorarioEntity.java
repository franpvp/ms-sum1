package com.duoc.citasmedicas.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "HORARIO")
public class HorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer idHorario;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "disponible")
    private boolean disponible;

    public HorarioEntity(){

    }

    public HorarioEntity(Integer idHorario, LocalDateTime fechaHora, boolean disponible) {
        this.idHorario = idHorario;
        this.fechaHora = fechaHora;
        this.disponible = disponible;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Horario{" +
                "idHorario=" + idHorario +
                ", fechaHora=" + fechaHora +
                ", disponible=" + disponible +
                '}';
    }
}
