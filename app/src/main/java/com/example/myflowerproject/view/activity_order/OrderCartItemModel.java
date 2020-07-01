package com.example.myflowerproject.view.activity_order;

public class OrderCartItemModel {

    private String itemImage;
    private int itemQuantity;

    public OrderCartItemModel(String itemImage, int itemQuantity) {
        this.itemImage = itemImage;
        this.itemQuantity = itemQuantity;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
