package com.softsquared.runtastic.src.main.fragment.Profile.interfaces;

public interface FragmentProfileView {
    void validateSuccess(String text, int code);

    void validateFailure(String message);

    void getProfileResult(String name, String createdAt, int friendCnt);
}
