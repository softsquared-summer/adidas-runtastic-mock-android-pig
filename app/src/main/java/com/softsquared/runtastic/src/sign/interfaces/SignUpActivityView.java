package com.softsquared.runtastic.src.sign.interfaces;

public interface SignUpActivityView {
    void validateSuccess(String text);

    void validateFailure(String message);

    void validateCode(int code,int userNo);

    void putJwtToken(String jwt);
}
