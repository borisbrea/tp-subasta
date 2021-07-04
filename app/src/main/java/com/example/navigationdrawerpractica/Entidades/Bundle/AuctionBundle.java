package com.example.navigationdrawerpractica.Entidades.Bundle;

import java.io.Serializable;

public class AuctionBundle implements Serializable {

    private String  userId;
    private String  auctionId;
    private String  catalogId;
    private String  catalogDescription;
    private String  indexCatalog;

    public AuctionBundle() {
    }

    public AuctionBundle(String userId, String auctionId, String indexCatalog) {
        this.userId = userId;
        this.auctionId = auctionId;
        this.indexCatalog = indexCatalog;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public String getIndexCatalog() {
        return indexCatalog;
    }

    public void setIndexCatalog(String indexCatalog) {
        this.indexCatalog = indexCatalog;
    }
}
