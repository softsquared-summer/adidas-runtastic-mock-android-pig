package com.softsquared.runtastic.src.login.models;

import com.google.gson.annotations.SerializedName;

public class SetBodyRequest {
    @SerializedName("userNo")
    int userNo;
    @SerializedName("height")
    String height;
    @SerializedName("heightType")
    int heightType;
    @SerializedName("weight")
    String weight;
    @SerializedName("weightType")
    int weightType;

    public SetBodyRequest(int userNo, String height, int heightType, String weight, int weightType) {
        this.userNo = userNo;
        this.height = height;
        this.heightType = heightType;
        this.weight = weight;
        this.weightType = weightType;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getHeightType() {
        return heightType;
    }

    public void setHeightType(int heightType) {
        this.heightType = heightType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getWeightType() {
        return weightType;
    }

    public void setWeightType(int weightType) {
        this.weightType = weightType;
    }
}
