package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.OrderBill;
import com.example.myflowerproject.model.entity.Users;
import com.google.gson.annotations.SerializedName;

public class OrderBillResult extends BaseResult{
    @SerializedName("data")
    private OrderBill orderBill;

    public OrderBill getOrderBill() {
        return orderBill;
    }

    public void setOrderBill(OrderBill orderBill) {
        this.orderBill = orderBill;
    }
}