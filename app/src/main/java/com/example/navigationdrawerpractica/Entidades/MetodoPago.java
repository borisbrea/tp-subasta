package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class MetodoPago implements Serializable {

    private String  id;
    private String  metodo;
    private String  tipo;
    private String  numeroTarjeta;
    private String  nombreBanco;
    private String  numeroCuentaCorriente;
    private boolean validado;

    public MetodoPago() {
    }

    public MetodoPago(String id, String metodo, String tipo, String numeroTarjeta, String nombreBanco, String numeroCuentaCorriente, boolean validado) {
        this.id = id;
        this.metodo = metodo;
        this.tipo = tipo;
        this.numeroTarjeta = numeroTarjeta;
        this.nombreBanco = nombreBanco;
        this.numeroCuentaCorriente = numeroCuentaCorriente;
        this.validado = validado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuentaCorriente() {
        return numeroCuentaCorriente;
    }

    public void setNumeroCuentaCorriente(String numeroCuentaCorriente) {
        this.numeroCuentaCorriente = numeroCuentaCorriente;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

}
