package com.example.myflowerproject.ui.OrderDetail;

public class ProductDetailModel {
    public Integer img;
    public String sl;

    public ProductDetailModel(Integer img, String sl) {
        this.img = img;
        this.sl = sl;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }
}
