package com.softsquared.runtastic.src.main.fragment.Status.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.BrandListItem;

import java.util.ArrayList;

public class BrandResponse {
    @SerializedName("result")
    ArrayList<BrandListItem> result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("messgae")
    String message;

    public ArrayList<BrandListItem> getResult() {
        return result;
    }

    public void setResult(ArrayList<BrandListItem> result) {
        this.result = result;
    }

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
