package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResult extends BaseResult {
    @SerializedName("data")
    private List<Category> categoryList;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
