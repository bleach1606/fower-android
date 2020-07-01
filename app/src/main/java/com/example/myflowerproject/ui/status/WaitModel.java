package com.example.myflowerproject.ui.status;

public class WaitModel {
    public String img;
    public String quantity;
    public String money;
    public int id;

    public WaitModel(String img, String quantity, String money) {
        this.img = img;
        this.quantity = quantity;
        this.money = money;
    }

    public WaitModel(String img, String quantity, String money, int id) {
        this.img = img;
        this.quantity = quantity;
        this.money = money;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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
