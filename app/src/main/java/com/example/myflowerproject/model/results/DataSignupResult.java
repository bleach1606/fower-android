package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.Users;
import com.google.gson.annotations.SerializedName;

public class DataSignupResult {
    @SerializedName("success")
    private String result;

    @SerializedName("data")
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
