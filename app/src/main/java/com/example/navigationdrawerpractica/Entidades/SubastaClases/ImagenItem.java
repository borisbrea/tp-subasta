package com.example.navigationdrawerpractica.Entidades.SubastaClases;

import java.io.Serializable;

public class ImagenItem implements Serializable {

    private String url;

    public ImagenItem() {
    }

    public ImagenItem(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
