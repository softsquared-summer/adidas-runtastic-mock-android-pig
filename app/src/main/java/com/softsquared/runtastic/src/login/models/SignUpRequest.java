package com.softsquared.runtastic.src.login.models;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest {
    @SerializedName("lName")
    String lName;

    @SerializedName("fName")
    String fName;

    @SerializedName("sex")
    int sex;

    @SerializedName("email")
    String email;

    @SerializedName("pw")
    String pw;

    @SerializedName("birth")
    int birth;

    @SerializedName("profileImage")
    String profileImage;

    public SignUpRequest(String lName, String fName, int sex, String email, String pw, int birth, String profileImage) {
        this.lName = lName;
        this.fName = fName;
        this.sex = sex;
        this.email = email;
        this.pw = pw;
        this.birth = birth;
        this.profileImage = profileImage;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
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
