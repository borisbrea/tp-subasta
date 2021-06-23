package com.example.navigationdrawerpractica.Entidades.SubastaClases;

import java.io.Serializable;
import java.util.List;

public class SubastaConArticulos implements Serializable {

    private int               auctionId;
    private List<ItemSubasta> articles;

    public SubastaConArticulos() {
    }

    public SubastaConArticulos(int auctionId, List<ItemSubasta> articles) {
        this.auctionId = auctionId;
        this.articles = articles;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public List<ItemSubasta> getArticles() {
        return articles;
    }

    public void setArticles(List<ItemSubasta> articles) {
        this.articles = articles;
    }
}
