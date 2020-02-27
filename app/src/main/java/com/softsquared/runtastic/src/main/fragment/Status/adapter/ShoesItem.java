package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import com.google.gson.annotations.SerializedName;

public class ShoesItem {
    @SerializedName("brandNo")
    private String brandNo;

    @SerializedName("modelNo")
    private String modelNo;

    @SerializedName("modelName")
    private String modelName;

    @SerializedName("imageUrl")
    private String imageUrl;

    public ShoesItem(String brandNo, String modelNo, String modelName, String imageUrl) {
        this.brandNo = brandNo;
        this.modelNo = modelNo;
        this.modelName = modelName;
        this.imageUrl = imageUrl;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
