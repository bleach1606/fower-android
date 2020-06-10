package com.example.myflowerproject.ui.home;

import com.example.myflowerproject.model.entity.FlowerProducts;

import java.util.List;

public class HomePageModel {

    public static final int BANNERSLIDER = 0;
    public static final int HORIZONTAL_PRODUCT_PREVIEW = 1;
    public static final int GRID_PRODUCT_PREVIEW = 2;
    private int type;
    private int typeProduct;
    private List<SliderModel> sliderModelList;
    private String title;
    private List<FlowerProducts> flowerProductsList;

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    public HomePageModel(int type, String title, List<FlowerProducts> previewItemModelList, int typeProduct) {
        this.type = type;
        this.title = title;
        this.flowerProductsList = previewItemModelList;
        this.typeProduct = typeProduct;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(int typeProduct) {
        this.typeProduct = typeProduct;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FlowerProducts> getFlowerProductsList() {
        return flowerProductsList;
    }

    public void setFlowerProductsList(List<FlowerProducts> flowerProductsList) {
        this.flowerProductsList = flowerProductsList;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }
}
