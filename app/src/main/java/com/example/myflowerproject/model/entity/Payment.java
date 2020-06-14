package com.example.myflowerproject.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class Payment {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("money")
    @Expose
    private double money;

    @SerializedName("timeOfTransaction")
    @Expose
    private Time timeOfTransaction;

    public Payment(double money, String kind, Boolean active) {
        this.money = money;
        this.kind = kind;
        this.active = active;
    }

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("active")
    @Expose
    private Boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Time getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(Time timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
