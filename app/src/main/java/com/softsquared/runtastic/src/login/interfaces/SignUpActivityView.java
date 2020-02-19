package com.softsquared.runtastic.src.login.interfaces;

public interface SignUpActivityView {
    void validateSuccess(String text);

    void validateFailure(String message);

    void validateCode(int code);
}
