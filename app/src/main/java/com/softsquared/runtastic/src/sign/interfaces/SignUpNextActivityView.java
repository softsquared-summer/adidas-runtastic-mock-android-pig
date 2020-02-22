package com.softsquared.runtastic.src.sign.interfaces;

public interface SignUpNextActivityView{
    void validateSuccess(String text,int code);

    void setBodySuccess(String text);

    void setGoalSuccess(String text);

    void validateFailure(String message);
}
