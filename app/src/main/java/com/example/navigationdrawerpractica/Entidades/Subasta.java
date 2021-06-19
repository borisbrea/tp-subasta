package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;
import java.util.Date;

public class Subasta implements Serializable {

    int     id;
    String  title;
    String  status;
    String  category;
    String  image;

    String  fecha;
    String  subastador;
    String  ubicacion;
    int     capacidadAsistentes;
    boolean tieneDeposito;
    boolean seguridadPropia;


    public Subasta() {}

    public Subasta(int id, String fecha, String category) {
        this.id = id;
        this.fecha = fecha;
        this.category = category;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
