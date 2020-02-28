package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.models.SneakersRequest;
import com.softsquared.runtastic.src.main.fragment.Status.models.SneakersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddShoes3StepRetrofitInterface {

    @POST("/sneakers")
    Call<SneakersResponse> postSneakersInfo(@Body SneakersRequest request);
}
