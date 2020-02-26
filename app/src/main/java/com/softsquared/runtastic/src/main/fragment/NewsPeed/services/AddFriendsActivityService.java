package com.softsquared.runtastic.src.main.fragment.NewsPeed.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.AddFriendsActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.AddFriendsActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.FriendListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class AddFriendsActivityService {
    final AddFriendsActivityView mAddFriendsActivityView;

    public AddFriendsActivityService(AddFriendsActivityView addFriendsActivityView) {
        this.mAddFriendsActivityView = addFriendsActivityView;
    }

    public void getFriendsList() {
        final AddFriendsActivityRetrofitInterface addFriendsActivityRetrofitInterface = getRetrofit().create(AddFriendsActivityRetrofitInterface.class);
        addFriendsActivityRetrofitInterface.getFriends().enqueue(new Callback<FriendListResponse>() {
            @Override
            public void onResponse(Call<FriendListResponse> call, Response<FriendListResponse> response) {
                final FriendListResponse friendListResponse = response.body();
                if(friendListResponse == null) {
                    mAddFriendsActivityView.validateFailure(null);
                    Log.e("[Log.e] 친구목록 불러오기 실패", " friendListResponse is null");
                    return;
                }
                if(friendListResponse.getCode() == 100) {
                    if(friendListResponse.getResult() == null) {
                        friendListResponse.setMessage("친구 없음");
                        mAddFriendsActivityView.validateSuccess(friendListResponse.getMessage());
                        Log.e("[Log.e] 친구목록 불러오기 성공", friendListResponse.getMessage());
                    } else {
                        mAddFriendsActivityView.validateSuccess(friendListResponse.getMessage());
                        Log.e("[Log.e] 친구목록 불러오기 성공", friendListResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<FriendListResponse> call, Throwable t) {
                mAddFriendsActivityView.validateFailure(null);
                Log.e("[Log.e] 친구목록 불러오기 실패", "onFailure" + t.getLocalizedMessage());
            }
        });
    }
}
