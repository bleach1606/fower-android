package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.Users;
import com.google.gson.annotations.SerializedName;

public class UserResult extends BaseResult {
    @SerializedName("data")
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
