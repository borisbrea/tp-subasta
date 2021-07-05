package com.example.navigationdrawerpractica.Entidades.requestEntities;

import java.io.Serializable;
import java.util.List;

public class ArticleRequest implements Serializable {

    private String userId;

    private String title;
    private String description;
    private String fullDescription;
    private List<String> images;

    public ArticleRequest() {
    }

    public ArticleRequest(String userId, String title, String description, String fullDescription, List<String> images) {
        this.userId          = userId;
        this.title           = title;
        this.description     = description;
        this.fullDescription = fullDescription;
        this.images          = images;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
