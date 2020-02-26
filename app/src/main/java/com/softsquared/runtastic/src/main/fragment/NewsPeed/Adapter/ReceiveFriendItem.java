package com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter;

import com.google.gson.annotations.SerializedName;

public class ReceiveFriendItem {
    @SerializedName("requestNo")
    String requestNo;

    @SerializedName("senderNo")
    String senderNo;

    @SerializedName("lastName")
    String lastName;

    @SerializedName("firstName")
    String firstName;

    @SerializedName("profileImage")
    String profileImage;

    public ReceiveFriendItem(String requestNo, String senderNo, String lastName, String firstName, String profileImage) {
        this.requestNo = requestNo;
        this.senderNo = senderNo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileImage = profileImage;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getSenderNo() {
        return senderNo;
    }

    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo;
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
