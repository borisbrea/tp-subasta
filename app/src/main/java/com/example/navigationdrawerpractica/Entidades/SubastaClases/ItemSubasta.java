package com.example.navigationdrawerpractica.Entidades.SubastaClases;

import java.io.Serializable;
import java.util.List;

public class ItemSubasta implements Serializable {

    private int     catalogId;
    private String  title;
    private String  status;
    private boolean canBid;
    private String  description;
    private String  catalogDescription;
    private String  owner;
    private String  basePrice;
    private List<ImagenItem> pictures;

    public ItemSubasta() {
    }

    public ItemSubasta(int catalogId, String title, String status, boolean canBid, String description, String catalogDescription, String owner, String basePrice, List<ImagenItem> pictures) {
        this.catalogId   = catalogId;
        this.title       = title;
        this.status      = status;
        this.canBid      = canBid;
        this.description = description;
        this.description = catalogDescription;
        this.owner       = owner;
        this.basePrice   = basePrice;
        this.pictures    = pictures;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
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

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
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