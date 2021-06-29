package com.example.navigationdrawerpractica.Entidades.SubastaClases;

import java.io.Serializable;
import java.util.List;

public class ItemSubasta implements Serializable {

    private String  title;
    private String  status;
    private boolean canBid;
    private String  description;
    private String  owner;
    private String  basePrice;
    private List<ImagenItem> pictures;

    public ItemSubasta() {
    }

    public ItemSubasta(String title, String status, boolean canBid, String description, String owner, String basePrice, List<ImagenItem> pictures) {
        this.title       = title;
        this.status      = status;
        this.canBid      = canBid;
        this.description = description;
        this.owner       = owner;
        this.basePrice   = basePrice;
        this.pictures    = pictures;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isCanBid() {
        return canBid;
    }

    public void setCanBid(boolean canBid) {
        this.canBid = canBid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public List<ImagenItem> getPictures() {
        return pictures;
    }

    public void setPictures(List<ImagenItem> pictures) {
        this.pictures = pictures;
    }
}