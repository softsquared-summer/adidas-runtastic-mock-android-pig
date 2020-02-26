package com.softsquared.runtastic.src.main.fragment.NewsPeed.models;

import com.google.gson.annotations.SerializedName;

public class RequestNumber {
    @SerializedName("requestNo")
    int requestNo;

    public RequestNumber(int requestNo) {
        this.requestNo = requestNo;
    }

    public int getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(int requestNo) {
        this.requestNo = requestNo;
    }
}
