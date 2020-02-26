package com.softsquared.runtastic.src.main.fragment.NewsPeed.models;

import com.google.gson.annotations.SerializedName;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.FriendsListItem;

import java.util.ArrayList;

public class FriendListResponse {
    @SerializedName("result")
    ArrayList<FriendsListItem> result;

    @SerializedName("isSuccess")
    boolean isSuccess;

    @SerializedName("code")
    int code;

    @SerializedName("messgae") // 오타 수정
    String message;

    public ArrayList<FriendsListItem> getResult() {
        return result;
    }

    public void setResult(ArrayList<FriendsListItem> result) {
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
