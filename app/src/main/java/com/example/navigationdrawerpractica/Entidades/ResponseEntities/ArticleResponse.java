package com.example.navigationdrawerpractica.Entidades.ResponseEntities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ArticleResponse implements Serializable {

    private List<String>    images;
    private String          description;
    private String          fullDescription;

    private String          productStatus;
    private BigDecimal      basePrice;
    private BigDecimal      commission;
    private Date            assignedDate;
    private Integer         assignedAuction;
    private Date            soldDate;
    private BigDecimal      soldAmount;
    private BigDecimal      earnings;

    public ArticleResponse() {
    }

    public ArticleResponse(List<String> images, String description, String fullDescription, String productStatus, BigDecimal basePrice, BigDecimal commission, Date assignedDate, Integer assignedAuction, Date soldDate, BigDecimal soldAmount, BigDecimal earnings) {
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Integer getAssignedAuction() {
        return assignedAuction;
    }

    public void setAssignedAuction(Integer assignedAuction) {
        this.assignedAuction = assignedAuction;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public BigDecimal getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(BigDecimal soldAmount) {
        this.soldAmount = soldAmount;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }
}
