package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.ReceiveListAdapter;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.ReceiveFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.RequestNumber;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

public interface FriendAlarmActivityRetrofitInterface {
    @GET("/friend/request")
    Call<ReceiveFriendResponse> getReceive();

    @HTTP(method = "DELETE", path = "/friend/request/accept", hasBody = true)
    Call<AddFriendResponse> acceptFriend(@Body RequestNumber number);
}
