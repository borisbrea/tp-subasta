package com.example.navigationdrawerpractica.Entidades.requestEntities;

import java.io.Serializable;

public class AccountRequest implements Serializable {

    private String type;
    private String company;
    private String number;

    public AccountRequest() {
    }

    public AccountRequest(String type, String company, String number) {
        this.type = type;
        this.company = company;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
