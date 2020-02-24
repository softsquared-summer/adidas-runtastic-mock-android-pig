package com.softsquared.runtastic.src.main.fragment.Profile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {
    @SerializedName("isSuccess")
    @Expose
    private boolean isSuccess;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("result")
    @Expose
    private Result result;

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {
        @SerializedName("name")
        @Expose
        String name;
        @SerializedName("createdAt")
        @Expose
        String createdAt;
        @SerializedName("friendCnt")
        @Expose
        String friendCnt;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getFriendCnt() {
            return friendCnt;
        }

        public void setFriendCnt(String friendCnt) {
            this.friendCnt = friendCnt;
        }
    }
}
