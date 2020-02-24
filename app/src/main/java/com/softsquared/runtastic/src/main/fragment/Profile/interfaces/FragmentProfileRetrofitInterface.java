package com.softsquared.runtastic.src.main.fragment.Profile.interfaces;

import com.softsquared.runtastic.src.main.fragment.Profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FragmentProfileRetrofitInterface {
    @GET("profile-tab")
    Call<ProfileResponse> getProfileTab();
}
