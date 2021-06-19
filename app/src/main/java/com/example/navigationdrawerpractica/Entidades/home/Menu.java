package com.example.navigationdrawerpractica.Entidades.home;

public class Menu {

    private String type;
    private String description;
    private String icon;

    public Menu() {
    }

    public Menu(String type, String description, String icon) {
        this.type = type;
        this.description = description;
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
