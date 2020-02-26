package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.FriendListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AddFriendsActivityRetrofitInterface {
    @GET("/friend")
    Call<FriendListResponse> getFriends();
}
