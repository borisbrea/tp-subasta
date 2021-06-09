package com.example.navigationdrawerpractica.Entidades;

import java.util.Date;

public class Subasta {

    int     id;
    String  fecha;
    String  estado;
    String  subastador;
    String  ubicacion;
    int     capacidadAsistentes;
    boolean tieneDeposito;
    boolean seguridadPropia;
    String  categoria;


    public Subasta(int id, String fecha, String categoria) {
        this.id = id;
        this.fecha = fecha;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSubastador() {
        return subastador;
    }

    public void setSubastador(String subastador) {
        this.subastador = subastador;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidadAsistentes() {
        return capacidadAsistentes;
    }

    public void setCapacidadAsistentes(int capacidadAsistentes) {
        this.capacidadAsistentes = capacidadAsistentes;
    }

    public boolean isTieneDeposito() {
        return tieneDeposito;
    }

    public void setTieneDeposito(boolean tieneDeposito) {
        this.tieneDeposito = tieneDeposito;
    }

    public boolean isSeguridadPropia() {
        return seguridadPropia;
    }

    public void setSeguridadPropia(boolean seguridadPropia) {
        this.seguridadPropia = seguridadPropia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
