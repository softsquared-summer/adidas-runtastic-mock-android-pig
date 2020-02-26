package com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendRequest;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.FindFriendResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FindFriendActivityRetrofitInterface {
    @GET("/search/friend")
    Call<FindFriendResponse> getFriendName(@Query("query") String email);

    @POST("/search/friend")
    Call<AddFriendResponse> postAddFriend(@Body AddFriendRequest request);
}
