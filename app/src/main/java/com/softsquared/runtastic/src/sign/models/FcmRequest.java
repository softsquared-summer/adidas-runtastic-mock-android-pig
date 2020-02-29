package com.softsquared.runtastic.src.sign.models;

import com.google.gson.annotations.SerializedName;

public class FcmRequest {
    @SerializedName("fcmToken")
    String fcmToken;

    public FcmRequest(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
