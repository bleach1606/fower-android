package com.example.myflowerproject.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {

    private int id;

    private Boolean fiActive;

    public Boolean getFiActive() {
        return fiActive;
    }

    public void setFiActive(Boolean fiActive) {
        this.fiActive = fiActive;
    }

    private String username;

    private String password;

    private String role;

    private int people_id;

    private People people;
}
