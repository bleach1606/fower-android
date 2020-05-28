package com.example.myflowerproject.model.entity;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    private int categoryIconLink;
    private String categoryName;
    private int type;

    public int getCategoryIconLink() {
        return categoryIconLink;
    }

    public void setCategoryIconLink(int categoryIconLink) {
        this.categoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CategoryModel(int categoryIconLink, String categoryName, int type) {
        this.categoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
        this.type = type;
    }
}
