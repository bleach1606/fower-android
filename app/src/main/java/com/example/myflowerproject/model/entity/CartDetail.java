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

    @SerializedName("orderBillId")
    @Expose
    private int orderBillId;
}
