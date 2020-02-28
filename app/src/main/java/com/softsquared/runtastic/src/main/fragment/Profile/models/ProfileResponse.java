package com.softsquared.runtastic.src.main.fragment.Profile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {
    @SerializedName("result")
    private ProfileResult result;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("messgae")
    private String message;

    public ProfileResult getResult() {
        return result;
    }

    public void setResult(ProfileResult result) {
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

    public class ProfileResult {
        @SerializedName("profileImage")
        String profileImage;
        @SerializedName("lastName")
        String lastName;
        @SerializedName("firstName")
        String firstName;
        @SerializedName("createdAt")
        String createdAt;
        @SerializedName("friendCnt")
        String friendCnt;

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
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
