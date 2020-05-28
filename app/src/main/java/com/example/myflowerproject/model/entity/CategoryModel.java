package com.example.myflowerproject.model.entity;

import java.io.Serializable;

public class CategoryModel implements Serializable {
    private int categoryIconLink;
    private String categoryName;

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

    public CategoryModel(int categoryIconLink, String categoryName) {
        this.categoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
    }
}
