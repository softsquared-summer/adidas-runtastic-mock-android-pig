package com.softsquared.runtastic.src.sign.interfaces;

import com.softsquared.runtastic.src.sign.models.FcmRequest;
import com.softsquared.runtastic.src.sign.models.FcmResponse;
import com.softsquared.runtastic.src.sign.models.SetBodyRequest;
import com.softsquared.runtastic.src.sign.models.SetBodyResponse;
import com.softsquared.runtastic.src.sign.models.SetGoalRequest;
import com.softsquared.runtastic.src.sign.models.SetGoalResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface SignUpNextRetrofitInterface {
    @PUT("/user/body/initial")
    Call<SetBodyResponse> putBodyProfile(@Body SetBodyRequest params);

    @POST("/user/goal/initial")
    Call<SetGoalResponse> postGoalProfile(@Body SetGoalRequest params);

    @POST("/fcmtoken")
    Call<FcmResponse> postFcmToken(@Body FcmRequest request);
}
