package com.example.myflowerproject.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class FlowerProducts {

    private int id;

    private Boolean fiActive;

    private String name;

    private int price;

    private String description;

    private String avatar;

    private int categoryId;

    private List<Cart> cartList;

}
