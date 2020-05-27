package com.example.myflowerproject.model.entity;

public class ProductSpecificationModel {

    private String productFeatureName;
    private String productFeatureValue;

    public ProductSpecificationModel(String productFeatureName, String productFeatureValue) {
        this.productFeatureName = productFeatureName;
        this.productFeatureValue = productFeatureValue;
    }

    public String getProductFeatureName() {
        return productFeatureName;
    }

    public void setProductFeatureName(String productFeatureName) {
        this.productFeatureName = productFeatureName;
    }

    public String getProductFeatureValue() {
        return productFeatureValue;
    }

    public void setProductFeatureValue(String productFeatureValue) {
        this.productFeatureValue = productFeatureValue;
    }
}
