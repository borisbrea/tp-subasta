package com.example.navigationdrawerpractica.Entidades.requestEntities;

import java.io.Serializable;

public class BidRequest implements Serializable {

    private int catalogId;
    private int amount;

    public BidRequest() {
    }

    public BidRequest(int catalogId, int amount) {
        this.catalogId = catalogId;
        this.amount = amount;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
