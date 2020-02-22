package com.softsquared.runtastic.src.sign.interfaces;

import com.softsquared.runtastic.src.login.models.LoginRequest;
import com.softsquared.runtastic.src.login.models.LoginResponse;
import com.softsquared.runtastic.src.sign.models.SignUpRequest;
import com.softsquared.runtastic.src.sign.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {
    @POST("/user")
    Call<SignUpResponse> postSIgnUp(@Body SignUpRequest params);
    
    @POST("/jwt")
    Call<LoginResponse> postLogin(@Body LoginRequest params);
}
