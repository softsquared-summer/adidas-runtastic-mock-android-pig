package com.softsquared.runtastic.src.login.interfaces;

import com.softsquared.runtastic.src.login.models.SetBodyRequest;
import com.softsquared.runtastic.src.login.models.SetBodyResponse;
import com.softsquared.runtastic.src.login.models.SetGoalRequest;
import com.softsquared.runtastic.src.login.models.SetGoalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpNextRetrofitInterface {
    @POST("/user/body/initial")
    Call<SetBodyResponse> postBodyProfile(@Body SetBodyRequest params);

    @POST("/user/goal/initial")
    Call<SetGoalResponse> postBodyProfile(@Body SetGoalRequest params);
}
