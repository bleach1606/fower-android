package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.OrderBill;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOrderBillResult extends BaseResult {
    @SerializedName("data")
    private List<OrderBill> orderBillList;

    public List<OrderBill> getOrderBillList() {
        return orderBillList;
    }

    public void setOrderBillList(List<OrderBill> orderBillList) {
        this.orderBillList = orderBillList;
    }

}
