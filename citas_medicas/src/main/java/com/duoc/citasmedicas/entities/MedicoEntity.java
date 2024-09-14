package com.duoc.citasmedicas.entities;


import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name = "medico")
public class MedicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer idMedico;

    @Column(name = "rut", length = 12)
    private String rut;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "apellido_pat", length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_mat", length = 100)
    private String apellidoMaterno;

    @Column(name = "id_especialidad")
    private int idEspecialidad;

    public MedicoEntity(){

    }

    public MedicoEntity(Integer idMedico, String rut, String nombre, String apellidoPaterno, String apellidoMaterno, int idEspecialidad) {
        this.idMedico = idMedico;
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idEspecialidad = idEspecialidad;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
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

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "idMedico=" + idMedico +
                ", rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", idEspecialidad=" + idEspecialidad +
                '}';
    }
}
