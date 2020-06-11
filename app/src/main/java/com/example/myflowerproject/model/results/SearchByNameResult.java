package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.FlowerProducts;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchByNameResult {
    @SerializedName("data")
    private List<FlowerProducts> flowerProductsList;

    public List<FlowerProducts> getFlowerProductsList() {
        return flowerProductsList;
    }

    public void setFlowerProductsList(List<FlowerProducts> flowerProductsList) {
        this.flowerProductsList = flowerProductsList;
    }
}
