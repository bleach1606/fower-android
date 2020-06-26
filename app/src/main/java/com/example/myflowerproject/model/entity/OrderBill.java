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

    @SerializedName("payment")
    @Expose
    private Payment payment;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("receiverName")
    @Expose
    private String receiverName;

    @SerializedName("receiverTel")
    @Expose
    private String receiverTel;

    @SerializedName("receiverAddress")
    @Expose
    private String receiverAddress;

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
                ", payment=" + payment +
                ", address=" + address +
                ", cartDetailList=" + cartDetailList +
                '}';
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addCartDetail(CartDetail cd){
        if(this.cartDetailList==null) this.cartDetailList = new ArrayList<>();
        this.cartDetailList.add(cd);
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}