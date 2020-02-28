package com.softsquared.runtastic.src.main.fragment.Status.adapter;

import com.google.gson.annotations.SerializedName;

public class SneakersListItem {
    @SerializedName("sneakersNo")
    private String sneakersNo;
    @SerializedName("userNo")
    private String userNo;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("totalDistance")
    private String totalDistance;
    @SerializedName("limitDistance")
    private String limitDistance;

    public SneakersListItem(String sneakersNo, String userNo, String nickname, String imageUrl, String totalDistance, String limitDistance) {
        this.sneakersNo = sneakersNo;
        this.userNo = userNo;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.totalDistance = totalDistance;
        this.limitDistance = limitDistance;
    }

    public String getSneakersNo() {
        return sneakersNo;
    }

    public void setSneakersNo(String sneakersNo) {
        this.sneakersNo = sneakersNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getLimitDistance() {
        return limitDistance;
    }

    public void setLimitDistance(String limitDistance) {
        this.limitDistance = limitDistance;
    }
}
