package com.example.myflowerproject.model.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class People implements Serializable {

    private int id;

    private String name;

    private String address;

    private Date birthday;

    private String sex;

    private String job;

    private String email;

    private String phoneNumber;

    private String avatar;

}
