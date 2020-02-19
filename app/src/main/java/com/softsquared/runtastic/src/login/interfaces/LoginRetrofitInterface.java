package com.softsquared.runtastic.src.login.interfaces;

import com.softsquared.runtastic.src.login.models.LoginRequest;
import com.softsquared.runtastic.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {
    @POST("/jwt")
    Call<LoginResponse> postLogin(@Body LoginRequest params);
}
