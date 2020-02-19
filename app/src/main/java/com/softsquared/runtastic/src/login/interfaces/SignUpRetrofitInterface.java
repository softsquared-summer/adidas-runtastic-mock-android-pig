package com.softsquared.runtastic.src.login.interfaces;

import com.softsquared.runtastic.src.login.models.SignUpRequest;
import com.softsquared.runtastic.src.login.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    @POST("/user")
    Call<SignUpResponse> postSIgnUp(@Body SignUpRequest params);
}
