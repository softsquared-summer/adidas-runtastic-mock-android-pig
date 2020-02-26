package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.ReceiveFriendResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FriendAlarmActivityRetrofitInterface {
    @GET("/friend/request")
    Call<ReceiveFriendResponse> getReceive();
}
