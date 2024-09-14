package com.duoc.citasmedicas.entities;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name = "paciente")
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    private Integer idPaciente;

    @Column(name = "rut", length = 100)
    private String rut;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "apellido_pat", length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_mat", length = 100)
    private String apellidoMaterno;

    @Column(name = "id_prevision")
    private int idPrevision;

    public PacienteEntity(){

    }

    public PacienteEntity(Integer idPaciente, String rut, String nombre, String apellidoPaterno, String apellidoMaterno, int idPrevision) {
        this.idPaciente = idPaciente;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idPrevision = idPrevision;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(int idPrevision) {
        this.idPrevision = idPrevision;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "idPaciente=" + idPaciente +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", idPrevision=" + idPrevision +
                '}';
    }
}
