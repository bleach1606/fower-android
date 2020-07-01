package com.example.myflowerproject.ui.notification;

public class NotificationModel {
    public String status;
    public String notifications;
    public String image;

    public NotificationModel(String status, String notifications, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
