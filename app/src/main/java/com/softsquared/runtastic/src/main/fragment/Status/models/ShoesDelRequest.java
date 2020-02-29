package com.softsquared.runtastic.src.main.fragment.Status.models;

import com.google.gson.annotations.SerializedName;

public class ShoesDelRequest {
    @SerializedName("sneakersNo")
    int sneakersNo;

    public ShoesDelRequest(int sneakersNo) {
        this.sneakersNo = sneakersNo;
    }

    public int getSneakersNo() {
        return sneakersNo;
    }

    public void setSneakersNo(int sneakersNo) {
        this.sneakersNo = sneakersNo;
    }
}
