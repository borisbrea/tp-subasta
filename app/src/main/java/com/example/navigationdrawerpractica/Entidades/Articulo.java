package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class Articulo implements Serializable {

    private String id;
    private String fecha;
    private String disponible;
    private String descripcionCatalogo;
    private String descripcionCompleta;
    private String revisor;
    private String duenio;
    private String estado;

    private String imagen;

    public Articulo() {
    }

    public Articulo(String id, String fecha, String disponible, String descripcionCatalogo, String descripcionCompleta, String revisor, String duenio, String estado, String imagen) {
        this.id                     = id;
        this.fecha                  = fecha;
        this.disponible             = disponible;
        this.descripcionCatalogo    = descripcionCatalogo;
        this.descripcionCompleta    = descripcionCompleta;
        this.revisor                = revisor;
        this.duenio                 = duenio;
        this.estado                 = estado;
        this.imagen                 = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getDescripcionCatalogo() {
        return descripcionCatalogo;
    }

    public void setDescripcionCatalogo(String descripcionCatalogo) {
        this.descripcionCatalogo = descripcionCatalogo;
    }

    public String getDescripcionCompleta() {
        return descripcionCompleta;
    }

    public void setDescripcionCompleta(String descripcionCompleta) {
        this.descripcionCompleta = descripcionCompleta;
    }

    public String getRevisor() {
        return revisor;
    }

    public void setRevisor(String revisor) {
        this.revisor = revisor;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}