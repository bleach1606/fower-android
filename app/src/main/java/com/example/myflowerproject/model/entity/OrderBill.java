package com.example.myflowerproject.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBill {
    private int id;

    private Boolean active;

    private int status;

    private Date oderDate;

    private int users_id;

    private Users users;

    private List<Cart> cartList;

    public OrderBill() {
        cartList = new ArrayList<>();
    }
}
