package com.example.navigationdrawerpractica.Entidades.home;

public class Auction {

    private int           id;
    private String        title;
    private AuctionDetail detail;

    public Auction(){}

    public Auction(int id, String title, AuctionDetail detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuctionDetail getDetail() {
        return detail;
    }

    public void setDetail(AuctionDetail detail) {
        this.detail = detail;
    }
}
