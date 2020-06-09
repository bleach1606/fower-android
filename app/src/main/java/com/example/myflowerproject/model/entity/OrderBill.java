package com.example.myflowerproject.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBill {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("active")
    @Expose
    private Boolean active;

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("orderDate")
    @Expose
    private Date orderDate;

//    private Payment payment;

    @SerializedName("users")
    @Expose
    private Users users;

//    private Address address;

    @SerializedName("cartDetailList")
    @Expose
    private List<CartDetail> cartDetailList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }

    @Override
    public String toString() {
        return "OrderBill{" +
                "id=" + id +
                ", active=" + active +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", users=" + users +
                ", cartDetailList=" + cartDetailList +
                '}';
    }

    public void addCartDetail(CartDetail cd){
        if(this.cartDetailList==null) this.cartDetailList = new ArrayList<>();
        this.cartDetailList.add(cd);
    }
}