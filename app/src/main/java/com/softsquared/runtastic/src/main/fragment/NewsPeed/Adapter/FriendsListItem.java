package com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter;

import com.google.gson.annotations.SerializedName;

public class FriendsListItem {
    @SerializedName("friendNo")
    private String friendNo;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("profileImage")
    private String profileImage;

    public FriendsListItem(String friendNo, String lastName, String firstName, String profileImage) {
        this.friendNo = friendNo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.profileImage = profileImage;
    }

    public String getFriendNo() {
        return friendNo;
    }

    public void setFriendNo(String friendNo) {
        this.friendNo = friendNo;
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
