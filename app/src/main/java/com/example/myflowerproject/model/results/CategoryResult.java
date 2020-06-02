package com.example.myflowerproject.model.results;

import com.example.myflowerproject.model.entity.CategoryModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResult extends BaseResult {
    @SerializedName("data")
    private List<CategoryModel> categoryModelList;

    public List<CategoryModel> getCategoryModelList() {
        return categoryModelList;
    }

    public void setCategoryModelList(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }
}
