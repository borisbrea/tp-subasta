package com.example.navigationdrawerpractica.Entidades.Statics;

public class CategoryParticipation {

    private String  category;
    private Integer value;

    public CategoryParticipation() {
    }

    public CategoryParticipation(String category, Integer value) {
        this.category = category;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
