package com.softsquared.runtastic.src.sign.interfaces;

import com.softsquared.runtastic.src.sign.models.SetBodyRequest;
import com.softsquared.runtastic.src.sign.models.SetBodyResponse;
import com.softsquared.runtastic.src.sign.models.SetGoalRequest;
import com.softsquared.runtastic.src.sign.models.SetGoalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpNextRetrofitInterface {
    @POST("/user/body/initial")
    Call<SetBodyResponse> postBodyProfile(@Body SetBodyRequest params);

    @POST("/user/goal/initial")
    Call<SetGoalResponse> postGoalProfile(@Body SetGoalRequest params);
}
