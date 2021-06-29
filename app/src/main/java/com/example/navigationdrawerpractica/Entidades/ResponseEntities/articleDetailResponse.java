package com.example.navigationdrawerpractica.Entidades.ResponseEntities;

import com.example.navigationdrawerpractica.Entidades.SubastaClases.ImagenItem;

import java.io.Serializable;
import java.util.List;

public class articleDetailResponse implements Serializable {

    private int     id;
    private String  titulo;
    private String  descripcion;
    private String  estado;
    private String  precioBase;
    private String  comision;
    private String  fechaSubasta;
    private String  montoVendido;
    private String  ganancia;
    private List<ImagenItem> imagenes;

    public articleDetailResponse() {
    }

    public articleDetailResponse(int id, String  titulo, String descripcion, String estado, String precioBase, String comision, String fechaSubasta, String montoVendido, String ganancia, List<ImagenItem> imagenes) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.precioBase = precioBase;
        this.comision = comision;
        this.fechaSubasta = fechaSubasta;
        this.montoVendido = montoVendido;
        this.ganancia = ganancia;
        this.imagenes = imagenes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(String precioBase) {
        this.precioBase = precioBase;
    }

    public String getComision() {
        return comision;
    }

    public void setComision(String comision) {
        this.comision = comision;
    }

    public String getFechaSubasta() {
        return fechaSubasta;
    }

    public void setFechaSubasta(String fechaSubasta) {
        this.fechaSubasta = fechaSubasta;
    }

    public String getMontoVendido() {
        return montoVendido;
    }

    public void setMontoVendido(String montoVendido) {
        this.montoVendido = montoVendido;
    }

    public String getGanancia() {
        return ganancia;
    }

    public void setGanancia(String ganancia) {
        this.ganancia = ganancia;
    }

    public List<ImagenItem> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenItem> imagenes) {
        this.imagenes = imagenes;
    }
}
