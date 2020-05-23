package com.example.myflowerproject.model.results;

import com.google.gson.annotations.SerializedName;

public class BaseResult {

    public String getResultMessage() {
        return message;
    }

    public void setResultMessage(String message) {
        this.message = message;
    }

    @SerializedName("message")
    private String message;

    @SerializedName("total")
    private int total;

    public boolean success(){
        if(message == null) return false;
        else return message.equals("success");
    }

    public BaseResult() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
