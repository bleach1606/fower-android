package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.Users;
import com.google.gson.annotations.SerializedName;

public class DataLoginResult {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("tokenType")
    private String tokenType;

    @SerializedName("user")
    private Users user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
