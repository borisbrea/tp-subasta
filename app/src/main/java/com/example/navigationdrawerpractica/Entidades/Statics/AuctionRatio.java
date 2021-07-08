package com.example.navigationdrawerpractica.Entidades.Statics;

import java.io.Serializable;

public class AuctionRatio implements Serializable {

    private Long won;
    private Long lost;

    public AuctionRatio() {
    }

    public AuctionRatio(Long won, Long lost) {
        this.won  = won;
        this.lost = lost;
    }

    public Long getWon() {
        return won;
    }

    public void setWon(Long won) {
        this.won = won;
    }

    public Long getLost() {
        return lost;
    }

    public void setLost(Long lost) {
        this.lost = lost;
    }
}
