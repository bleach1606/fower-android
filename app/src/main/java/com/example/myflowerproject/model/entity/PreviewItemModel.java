package com.example.myflowerproject.model.entity;

public class PreviewItemModel {

//    private int productImage;
//    private String productName;
//    private String productPrice;
    private FlowerProducts products;

//    public PreviewItemModel(int productImage, String productName, String productPrice) {
//        this.productImage = productImage;
//        this.productName = productName;
//        this.productPrice = productPrice;
//    }
    public PreviewItemModel(FlowerProducts fp){
        this.products = fp;
//        productName = products.getName();
    }

    public FlowerProducts getProducts() {
        return products;
    }

    public void setProducts(FlowerProducts products) {
        this.products = products;
    }

//    public int getProductImage() {
//        return productImage;
//    }
//
//    public void setProductImage(int productImage) {
//        this.productImage = productImage;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productTitle) {
//        this.productName = productTitle;
//    }
//
//    public String getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(String productPrice) {
//        this.productPrice = productPrice;
//    }
}
