package com.example.navigationdrawerpractica.Entidades.home;

import java.util.List;

public class Filter {

    private String     name;
    private List<Type> types;

    public Filter() {
    }

    public Filter(String name, List<Type> types) {
        this.name = name;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }
}
