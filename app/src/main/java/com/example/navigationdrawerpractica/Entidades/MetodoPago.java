package com.example.navigationdrawerpractica.Entidades;

import java.io.Serializable;

public class MetodoPago implements Serializable {

    private int  id;
    private String  type;
    private String  company;
    private String  name;
    private String  number;

    public MetodoPago() {
    }

    public MetodoPago(int id, String type, String company, String name, String number) {
        this.id = id;
        this.type = type;
        this.company = company;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
