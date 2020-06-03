package com.example.myflowerproject.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("avatar")
    @Expose
    private int categoryIconLink;

    @SerializedName("name")
    @Expose
    private String categoryName;

//    @SerializedName("type")
//    @Expose
    private int type;
    public CategoryModel(){
        type = 1;
    }

    @SerializedName("flowerProductsList")
    @Expose
    private List<FlowerProducts> flowerProductsList;

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

    public CategoryModel(int categoryIconLink, String categoryName) {
        this.categoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
//        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<FlowerProducts> getFlowerProductsList() {
        return flowerProductsList;
    }

    public void setFlowerProductsList(List<FlowerProducts> flowerProductsList) {
        this.flowerProductsList = flowerProductsList;
    }
}
