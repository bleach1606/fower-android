package com.example.myflowerproject.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartDetail {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("flowerProduct")
    @Expose
    private FlowerProducts flowerProduct;

//    @SerializedName("orderBillId")
//    @Expose
//    private int orderBillId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public FlowerProducts getFlowerProduct() {
        return flowerProduct;
    }

    public void setFlowerProduct(FlowerProducts flowerProduct) {
        this.flowerProduct = flowerProduct;
    }
}