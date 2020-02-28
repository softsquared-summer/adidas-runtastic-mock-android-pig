package com.softsquared.runtastic.src.main.fragment.Status.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Sneakers implements Serializable {
    private int modelNo;

    private int sizeType;

    private String sizeValue;

    private String nickname;

    private int colorNo;

    private String startedAt;

    private int limitDistance;

    private int initDistance;

    public Sneakers() {
        this.modelNo = 1;
        this.sizeType = 5;
        this.sizeValue = "265";
        this.nickname = "my shoes";
        this.colorNo = 1;
        this.startedAt = "20200228";
        this.limitDistance = 500;
        this.initDistance = 0;
    }

    public Sneakers(int modelNo, int sizeType, String sizeValue, String nickname, int colorNo, String startedAt, int limitDistance, int initDistance) {
        this.modelNo = modelNo;
        this.sizeType = sizeType;
        this.sizeValue = sizeValue;
        this.nickname = nickname;
        this.colorNo = colorNo;
        this.startedAt = startedAt;
        this.limitDistance = limitDistance;
        this.initDistance = initDistance;
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

}
