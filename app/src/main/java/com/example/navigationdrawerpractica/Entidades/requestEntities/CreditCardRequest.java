package com.example.navigationdrawerpractica.Entidades.requestEntities;

import java.io.Serializable;

public class CreditCardRequest implements Serializable {

    private String type;
    private String company;
    private String bin;

    public CreditCardRequest() {
    }

    public CreditCardRequest(String type, String company, String bin) {
        this.type = type;
        this.company = company;
        this.bin = bin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }
}
