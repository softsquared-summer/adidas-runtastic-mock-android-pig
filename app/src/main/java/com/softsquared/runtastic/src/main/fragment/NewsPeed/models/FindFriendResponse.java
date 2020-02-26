package com.softsquared.runtastic.src.main.fragment.NewsPeed.models;

import com.google.gson.annotations.SerializedName;

public class FindFriendResponse {
    @SerializedName("result")
    FriendResult result;
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;

    public FriendResult getResult() {
        return result;
    }

    public void setResult(FriendResult result) {
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

    public class FriendResult {
        @SerializedName("userNo")
        private String userNo;
        @SerializedName("lastName")
        private String lastName;
        @SerializedName("firstName")
        private String firstName;
        @SerializedName("profileImage")
        private String profileImage;

        public FriendResult(String userNo, String lastName, String firstName, String profileImage) {
            this.userNo = userNo;
            this.lastName = lastName;
            this.firstName = firstName;
            this.profileImage = profileImage;
        }

        public String getUserNo() {
            return userNo;
        }

        public void setUserNo(String userNo) {
            this.userNo = userNo;
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

        public String getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(String profileImage) {
            this.profileImage = profileImage;
        }
    }
}
