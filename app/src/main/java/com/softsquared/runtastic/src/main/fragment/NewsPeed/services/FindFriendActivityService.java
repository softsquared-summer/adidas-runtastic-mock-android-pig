package com.softsquared.runtastic.src.main.fragment.NewsPeed.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FindFriendActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FindFriendActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendRequest;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.FindFriendResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class FindFriendActivityService {
    final FindFriendActivityView mFindFriendActivityView;

    public FindFriendActivityService(FindFriendActivityView findFriendActivityView){
        this.mFindFriendActivityView = findFriendActivityView;
    }

    public void getFriend(String email) {
        final FindFriendActivityRetrofitInterface findFriendActivityRetrofitInterface = getRetrofit().create(FindFriendActivityRetrofitInterface.class);
        findFriendActivityRetrofitInterface.getFriendName(email).enqueue(new Callback<FindFriendResponse>() {
            @Override
            public void onResponse(Call<FindFriendResponse> call, Response<FindFriendResponse> response) {
                final FindFriendResponse friendResponse = response.body();
                if(friendResponse == null) {
                    Log.e("[Log.e] 친구정보조회 실패", " friendResponse is null");
                    return;
                }
                if(friendResponse.getResult() == null) {
                    Log.e("[Log.e] 친구정보조회 성공", " : " + friendResponse.getMessage());
                    mFindFriendActivityView.sendNoInfo();
                } else {
                    Log.e("[Log.e] 친구정보조회 성공", " : ");
                    mFindFriendActivityView.getFriendsInfo(friendResponse.getResult());
                }
            }

            @Override
            public void onFailure(Call<FindFriendResponse> call, Throwable t) {
                Log.e("[Log.e] 친구정보조회 실패", " onFailure : " + t.getLocalizedMessage());
            }
        });
    }

    public void postRequestFriend(AddFriendRequest request) {
        final FindFriendActivityRetrofitInterface findFriendActivityRetrofitInterface = getRetrofit().create(FindFriendActivityRetrofitInterface.class);
        findFriendActivityRetrofitInterface.postAddFriend(request).enqueue(new Callback<AddFriendResponse>() {
            @Override
            public void onResponse(Call<AddFriendResponse> call, Response<AddFriendResponse> response) {
                final AddFriendResponse friendResponse = response.body();
                if(friendResponse == null) {
                    Log.e("[Log.e] 친구 요청 실패", " friendResponse is null");
                    mFindFriendActivityView.validateFailure(null);
                    return;
                }
                mFindFriendActivityView.validateSuccess(friendResponse.getMessage(),friendResponse.getCode());
            }

            @Override
            public void onFailure(Call<AddFriendResponse> call, Throwable t) {
                Log.e("[Log.e] 친구 요청 실패", " onFailure : " + t.getLocalizedMessage());
                mFindFriendActivityView.validateFailure(null);
            }
        });
    }
}
