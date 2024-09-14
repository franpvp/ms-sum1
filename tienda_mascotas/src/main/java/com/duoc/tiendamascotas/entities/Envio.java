package com.duoc.tiendamascotas.entities;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "envio")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_envio")
    private Integer idEnvio;

    @Column(name = "lista_productos")
    private List<Producto> listaProductos;

    @Column(name = "ubicacion_actual")
    private String ubicacionActual;

    @Column(name = "destino")
    private String destino;

    public Envio() {

    }

    public Envio(Integer idEnvio, List<Producto> listaProductos, String ubicacionActual, String destino) {
        this.idEnvio = idEnvio;
        this.listaProductos = listaProductos;
        this.ubicacionActual = ubicacionActual;
        this.destino = destino;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

}
