package com.example.myflowerproject.model.results;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserLoginResult extends BaseResult {

    @SerializedName("data")
    private DataLoginResult dataLoginResult;

    public DataLoginResult getDataLoginResult() {
        return dataLoginResult;
    }

    public void setDataLoginResult(DataLoginResult dataLoginResult) {
        this.dataLoginResult = dataLoginResult;
    }
}
