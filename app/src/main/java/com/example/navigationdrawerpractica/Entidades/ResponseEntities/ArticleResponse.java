package com.example.navigationdrawerpractica.Entidades.ResponseEntities;

import java.io.Serializable;
import java.util.List;

public class ArticleResponse implements Serializable {

    private List<String>    images;
    private String          description;
    private String          fullDescription;
    private String          productStatus;
    private int             basePrice;
    private int             commission;
    private String          assignedDate;
    private int             assignedAuction;
    private String          soldDate;
    private String          soldAmount;
    private String          earnings;

    public ArticleResponse() {
    }

    public ArticleResponse(List<String> images, String description, String fullDescription, String productStatus, int basePrice, int commission, String assignedDate, int assignedAuction, String soldDate, String soldAmount, String earnings) {
        this.images = images;
        this.description = description;
        this.fullDescription = fullDescription;
        this.productStatus = productStatus;
        this.basePrice = basePrice;
        this.commission = commission;
        this.assignedDate = assignedDate;
        this.assignedAuction = assignedAuction;
        this.soldDate = soldDate;
        this.soldAmount = soldAmount;
        this.earnings = earnings;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public int getAssignedAuction() {
        return assignedAuction;
    }

    public void setAssignedAuction(int assignedAuction) {
        this.assignedAuction = assignedAuction;
    }

    public String getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }

    public String getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(String soldAmount) {
        this.soldAmount = soldAmount;
    }

    public String getEarnigs() {
        return earnings;
    }

    public void setEarnigs(String earnigs) {
        this.earnings = earnigs;
    }
}
