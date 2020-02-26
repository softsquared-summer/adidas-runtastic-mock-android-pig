package com.softsquared.runtastic.src.sign.models;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("lastName")
    private String lastName;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("sex")
    private int sex;

    @SerializedName("email")
    private String email;

    @SerializedName("pw")
    private String pw;

    @SerializedName("birth")
    private int birth;

    @SerializedName("profileImage")
    private String profileImage;

    public SignUpRequest(String lastName, String firstName, int sex, String email, String pw, int birth, String profileImage) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.sex = sex;
        this.email = email;
        this.pw = pw;
        this.birth = birth;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
