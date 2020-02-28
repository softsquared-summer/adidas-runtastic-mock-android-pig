package com.softsquared.runtastic.src.main.fragment.Status.models;

import com.google.gson.annotations.SerializedName;

public class SneakersRequest {
    @SerializedName("modelNo")
    private int modelNo;

    @SerializedName("sizeType")
    private int sizeType;

    @SerializedName("sizeValue")
    private String sizeValue;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("colorNo")
    private int colorNo;

    @SerializedName("startedAt")
    private String startedAt;

    @SerializedName("limitDistance")
    private int limitDistance;

    @SerializedName("initDistance")
    private int initDistance;

    @SerializedName("imageUrl")
    private String imageUrl;

    public SneakersRequest(int modelNo, int sizeType, String sizeValue, String nickname, int colorNo, String startedAt, int limitDistance, int initDistance) {
        this.modelNo = modelNo;
        this.sizeType = sizeType;
        this.sizeValue = sizeValue;
        this.nickname = nickname;
        this.colorNo = colorNo;
        this.startedAt = startedAt;
        this.limitDistance = limitDistance;
        this.initDistance = initDistance;
        this.imageUrl = "";
    }

    public int getModelNo() {
        return modelNo;
    }

    public void setModelNo(int modelNo) {
        this.modelNo = modelNo;
    }

    public int getSizeType() {
        return sizeType;
    }

    public void setSizeType(int sizeType) {
        this.sizeType = sizeType;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getColorNo() {
        return colorNo;
    }

    public void setColorNo(int colorNo) {
        this.colorNo = colorNo;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public int getLimitDistance() {
        return limitDistance;
    }

    public void setLimitDistance(int limitDistance) {
        this.limitDistance = limitDistance;
    }

    public int getInitDistance() {
        return initDistance;
    }

    public void setInitDistance(int initDistance) {
        this.initDistance = initDistance;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
