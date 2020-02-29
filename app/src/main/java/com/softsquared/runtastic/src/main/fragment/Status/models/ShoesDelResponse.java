package com.softsquared.runtastic.src.main.fragment.Status.models;

import com.google.gson.annotations.SerializedName;

public class ShoesDelResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
