package com.example.myflowerproject.model.entity;

import lombok.Data;


@Data
public class Cart {

    private int id;

    private Boolean active;

    private int number;

    private int orderId;

    private int flowerProductsId;
}
