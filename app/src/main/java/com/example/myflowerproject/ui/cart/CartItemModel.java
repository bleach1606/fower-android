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

    ////////////  item

//    private int productImage;
//    private String productName;
//    private double productPrice;
//    private double productExPrice;
//    private int productQuantity;
    private CartDetail cd;

    public CartItemModel(int type, CartDetail cd) {
        this.type = type;
        this.cd = cd;
    }

    public CartDetail getCd() {
        return cd;
    }

    public void setCd(CartDetail cd) {
        this.cd = cd;
    }
//////////// item

}
