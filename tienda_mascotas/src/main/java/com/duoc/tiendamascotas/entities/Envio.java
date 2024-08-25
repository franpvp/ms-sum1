package com.duoc.tiendamascotas.entities;

import java.util.List;

public class Envio {

    private int idEnvio;
    private List<Producto> listaProductos;
    private String ubicacionActual;
    private String destino;

    public Envio(int idEnvio, List<Producto> listaProductos, String ubicacionActual, String destino) {
        this.idEnvio = idEnvio;
        this.listaProductos = listaProductos;
        this.ubicacionActual = ubicacionActual;
        this.destino = destino;

    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
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
