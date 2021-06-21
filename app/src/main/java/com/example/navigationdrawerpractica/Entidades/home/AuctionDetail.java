package com.example.navigationdrawerpractica.Entidades.home;

import java.io.Serializable;

public class AuctionDetail implements Serializable  {

    private String startDate;
    private String owner;
    private String category;
    private int    articleAmount;
    private String description;

    public AuctionDetail() {}

    public AuctionDetail(String startDate, String owner, String category, int articleAmount, String description) {
        this.startDate = startDate;
        this.owner = owner;
        this.category = category;
        this.articleAmount = articleAmount;
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getArticleAmount() {
        return articleAmount;
    }

    public void setArticleAmount(int articleAmount) {
        this.articleAmount = articleAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
