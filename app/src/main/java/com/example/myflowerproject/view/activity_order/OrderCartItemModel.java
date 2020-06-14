package com.example.myflowerproject.view.activity_order;

public class OrderCartItemModel {

    private int itemImage;
    private int itemQuantity;

    public OrderCartItemModel(int itemImage, int itemQuantity) {
        this.itemImage = itemImage;
        this.itemQuantity = itemQuantity;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
