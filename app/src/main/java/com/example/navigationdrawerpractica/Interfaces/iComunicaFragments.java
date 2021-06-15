package com.example.navigationdrawerpractica.Interfaces;

import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Subasta;

public interface iComunicaFragments {

    //Esta interface se encarga de realizar la comunicacion entre la lista de objetos y el detalle
    //(En la clase Persona se implementa Serializable para poder transportar un objeteo a otro)

    public void enviarPersona   (Persona persona);
    public void enviarSubasta   (Subasta subasta);
    public void enviarMetodoPago(MetodoPago metodoPago);
    public void enviarArticulo  (Articulo articulo);
}
