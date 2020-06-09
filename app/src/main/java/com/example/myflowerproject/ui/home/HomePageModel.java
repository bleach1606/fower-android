package com.example.myflowerproject.fragment;

import com.example.myflowerproject.model.entity.PreviewItemModel;
import com.example.myflowerproject.model.entity.SliderModel;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class HomePageModel {

    public static final int BANNERSLIDER = 0;
    public static final int HORIZONTAL_PRODUCT_PREVIEW = 1;
    public static final int GRID_PRODUCT_PREVIEW = 2;

    private int type;
    private int typeProduct;

    private List<SliderModel> sliderModelList;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    private String title;
    private List<PreviewItemModel> previewItemModelList;

    public HomePageModel(int type, String title, List<PreviewItemModel> previewItemModelList, int typeProduct) {
        this.type = type;
        this.title = title;
        this.previewItemModelList = previewItemModelList;
        this.typeProduct = typeProduct;
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

    public List<PreviewItemModel> getPreviewItemModelList() {
        return previewItemModelList;
    }

    public void setPreviewItemModelList(List<PreviewItemModel> previewItemModelList) {
        this.previewItemModelList = previewItemModelList;
    }

    /////////////////// Product preview

}
