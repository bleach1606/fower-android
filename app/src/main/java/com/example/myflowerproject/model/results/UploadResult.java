package com.example.myflowerproject.model.results;

import com.google.gson.annotations.SerializedName;

public class UploadResult extends BaseResult {
    @SerializedName("data")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
