package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.models.UserShoesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ManageShoesActivityRetrofitInterface {
    @GET("/user/sneakers")
    Call<UserShoesResponse> getUserSneakers();
}
