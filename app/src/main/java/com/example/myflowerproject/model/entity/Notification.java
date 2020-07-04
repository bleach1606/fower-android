package com.example.myflowerproject.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notification {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("check")
    @Expose
    private boolean isCheck;

    @SerializedName("avatar")
    @Expose
    public String image;

    public Notification() {
    }

    public Notification(int id, String title, String content, Date date, boolean isCheck, String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.isCheck = isCheck;
        this.image = image;
    }

    public Notification(String title, String content, String image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
