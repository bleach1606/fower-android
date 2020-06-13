package com.example.myflowerproject.ui.status;

public class ReceivedModel {
    public Integer img;
    public String quantity;
    public String money;

    public ReceivedModel(Integer img, String quantity, String money) {
        this.img = img;
        this.quantity = quantity;
        this.money = money;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
