package com.softsquared.runtastic.src.main.fragment.NewsPeed.models;

import com.google.gson.annotations.SerializedName;

public class AddFriendRequest {
    @SerializedName("userNo")
    private int userNo;

    public AddFriendRequest(int userNo) {
        this.userNo = userNo;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }
}
