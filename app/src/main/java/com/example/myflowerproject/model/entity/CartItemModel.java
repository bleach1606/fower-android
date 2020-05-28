package com.example.myflowerproject.model.entity;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;

    public CartItemModel(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    //////////// Cart item

    private int productImage;
    private String productName;
    private double productPrice;
    private double productExPrice;
    private int productQuantity;

    public CartItemModel(int type, int productImage, String productName, double productPrice, double productExPrice, int productQuantity) {
        this.type = type;
        this.productImage = productImage;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productExPrice = productExPrice;
        this.productQuantity = productQuantity;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductExPrice() {
        return productExPrice;
    }

    public void setProductExPrice(double productExPrice) {
        this.productExPrice = productExPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    //////////// Cart item

}
