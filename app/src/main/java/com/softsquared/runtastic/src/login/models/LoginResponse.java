package com.softsquared.runtastic.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("isSuccess")
    boolean isSuccess;
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("result")
    LoginResponseResult result;

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

    public LoginResponseResult getResult() {
        return result;
    }

    public void setResult(LoginResponseResult result) {
        this.result = result;
    }

    public class LoginResponseResult{
        @SerializedName("jwt")
        String jwt;

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
    }
}


