package com.example.navigationdrawerpractica.Entidades.home;

import com.example.navigationdrawerpractica.Entidades.Subasta;

import java.io.Serializable;
import java.util.List;

public class Home implements Serializable {

    private Menu              menu;
    private List<Filter>      filters;
    private List<AuctionHome> auctions;

    public Home() {
    }

    public Home(Menu menu, List<Filter> filters, List<AuctionHome> auctions) {
        this.menu = menu;
        this.filters = filters;
        this.auctions = auctions;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<AuctionHome> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionHome> auctions) {
        this.auctions = auctions;
    }
}
