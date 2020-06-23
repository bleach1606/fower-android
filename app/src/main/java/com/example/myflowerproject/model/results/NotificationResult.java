package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.Notification;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationResult extends BaseResult {
    @SerializedName("data")
    private List<Notification> list;

    public List<Notification> getList() {
        return list;
    }

    public void setList(List<Notification> list) {
        this.list = list;
    }
}
