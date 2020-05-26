package com.example.myflowerproject.model.entity;

public class NotificationModel {
    public String status;
    public String notifications;
    public Integer image;

    public NotificationModel(String status, String notifications, Integer image) {
        this.status = status;
        this.notifications = notifications;
        this.image = image;
    }

}
