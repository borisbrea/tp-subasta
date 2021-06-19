package com.example.navigationdrawerpractica.Entidades.home;

public class AuctionHome {

    	private int    id;
    	private String title;
    	private String category;
    	private String status;
        private String image;

    public AuctionHome() {
    }

    public AuctionHome(int id, String title, String category, String status, String image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.status = status;
        this.image = image;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
