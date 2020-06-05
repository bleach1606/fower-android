package com.example.myflowerproject.model.entity;

public class NotificationModel {
    public String status;
    public String notifications;
    public Integer image;
    public String hour;
    public String day;

    public NotificationModel(String status, String notifications, Integer image, String hour, String day) {
        this.status = status;
        this.notifications = notifications;
        this.image = image;
        this.hour = hour;
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
