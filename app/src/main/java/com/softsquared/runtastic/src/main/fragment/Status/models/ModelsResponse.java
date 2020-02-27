package com.softsquared.runtastic.src.main.fragment.Status.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.runtastic.src.main.fragment.Status.adapter.ShoesItem;

import java.util.ArrayList;

public class ModelsResponse {
    @SerializedName("result")
    private ArrayList<ShoesItem> result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("messgae")
    private String message;

    public ArrayList<ShoesItem> getResult() {
        return result;
    }

    public void setResult(ArrayList<ShoesItem> result) {
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
