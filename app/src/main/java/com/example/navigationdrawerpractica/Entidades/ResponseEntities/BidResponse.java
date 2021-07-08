package com.example.navigationdrawerpractica.Entidades.ResponseEntities;

import java.io.Serializable;
import java.math.BigDecimal;

public class BidResponse implements Serializable {

    private Integer id;
    private Integer catalogId;
    private BigDecimal amount;
    private String result;
    private String createdDate;

    public BidResponse() {
    }

    public BidResponse(Integer id, Integer catalogId, BigDecimal amount, String result, String createdDate) {
        this.id = id;
        this.catalogId = catalogId;
        this.amount = amount;
        this.result = result;
        this.createdDate = createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
