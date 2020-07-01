package com.example.myflowerproject.ui.status;

public class DeliveryModel {
    public String img;
    public String quantity;
    public String money;
    public String status;
    public String create;

    public DeliveryModel(String img, String quantity, String money) {
        this.img = img;
        this.quantity = quantity;
        this.money = money;
    }

    public DeliveryModel(String img, String quantity, String money, String status, String create) {
        this.img = img;
        this.quantity = quantity;
        this.money = money;
        this.status = status;
        this.create = create;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public DeliveryModel() {
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
