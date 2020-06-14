package com.example.myflowerproject.ui.notification;

public class NotificationModel {
    public String status;
    public String notifications;
    public Integer image;

    public NotificationModel(String status, String notifications, Integer image) {
        this.status = status;
        this.notifications = notifications;
        this.image = image;
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
}
