package com.example.navigationdrawerpractica.Entidades.ResponseEntities;

import com.example.navigationdrawerpractica.Entidades.MetodoPago;

import java.io.Serializable;
import java.util.List;

public class ResponseGetPaymentMethods implements Serializable {

    List<MetodoPago> paymentMethods;

    public ResponseGetPaymentMethods() {
    }

    public ResponseGetPaymentMethods(List<MetodoPago> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public List<MetodoPago> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<MetodoPago> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
