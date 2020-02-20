package com.softsquared.runtastic.src.login.interfaces;

public interface SignUpNextActivityView{
    void validateSuccess(String text,int code);

    void validateFailure(String message);
}
