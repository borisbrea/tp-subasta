package com.example.navigationdrawerpractica.Entidades.ResponseEntities;

import com.example.navigationdrawerpractica.Entidades.Statics.AuctionRatio;
import com.example.navigationdrawerpractica.Entidades.Statics.CategoryParticipation;

import java.io.Serializable;
import java.util.List;

public class StaticsResponse implements Serializable {

    private AuctionRatio                auctionRatio;
    private List<CategoryParticipation> categoryParticipation;

    public StaticsResponse() {
    }

    public StaticsResponse(AuctionRatio auctionRatio, List<CategoryParticipation> categoryParticipation) {
        this.auctionRatio = auctionRatio;
        this.categoryParticipation = categoryParticipation;
    }

    public AuctionRatio getAuctionRatio() {
        return auctionRatio;
    }

    public void setAuctionRatio(AuctionRatio auctionRatio) {
        this.auctionRatio = auctionRatio;
    }

    public List<CategoryParticipation> getCategoryParticipation() {
        return categoryParticipation;
    }

    public void setCategoryParticipation(List<CategoryParticipation> categoryParticipation) {
        this.categoryParticipation = categoryParticipation;
    }
}
