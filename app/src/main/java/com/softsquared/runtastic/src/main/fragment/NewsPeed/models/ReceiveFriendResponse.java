package com.softsquared.runtastic.src.main.fragment.NewsPeed.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.ReceiveFriendItem;

import java.util.ArrayList;

public class ReceiveFriendResponse {
    @SerializedName("result")
    ArrayList<ReceiveFriendItem> result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("message") // 오타 수정해야함
    String message;

    public ArrayList<ReceiveFriendItem> getResult() {
        return result;
    }

    public void setResult(ArrayList<ReceiveFriendItem> result) {
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
