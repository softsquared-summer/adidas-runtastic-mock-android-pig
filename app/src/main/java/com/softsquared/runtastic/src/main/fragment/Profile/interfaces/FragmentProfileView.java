package com.softsquared.runtastic.src.main.fragment.Profile.interfaces;

import com.softsquared.runtastic.src.main.fragment.Profile.models.ProfileResponse;

public interface FragmentProfileView {
    void validateSuccess(String text, int code);

    void validateFailure(String message);

    void getProfileResult(ProfileResponse.ProfileResult result);
}
