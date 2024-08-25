package com.duoc.citasmedicas.entities;

import java.time.LocalDateTime;

public class Horario {
    private int id;
    private LocalDateTime fechaHora;
    private boolean disponible;

    public Horario(int id, LocalDateTime fechaHora, boolean disponible) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
