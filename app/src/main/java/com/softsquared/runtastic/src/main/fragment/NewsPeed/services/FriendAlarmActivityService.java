package com.softsquared.runtastic.src.main.fragment.NewsPeed.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.Adapter.ReceiveListAdapter;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FriendAlarmActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FriendAlarmActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.AddFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.ReceiveFriendResponse;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.RequestNumber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class FriendAlarmActivityService {
    final FriendAlarmActivityView mFriendAlarmActivityView;

    public FriendAlarmActivityService(FriendAlarmActivityView friendAlarmActivityView) {
        mFriendAlarmActivityView = friendAlarmActivityView;
    }

    public void getReceiveList() {
        final FriendAlarmActivityRetrofitInterface friendAlarmActivityRetrofitInterface = getRetrofit().create(FriendAlarmActivityRetrofitInterface.class);
        friendAlarmActivityRetrofitInterface.getReceive().enqueue(new Callback<ReceiveFriendResponse>() {
            @Override
            public void onResponse(Call<ReceiveFriendResponse> call, Response<ReceiveFriendResponse> response) {
                final ReceiveFriendResponse receiveFriendResponse = response.body();
                if(receiveFriendResponse == null) {
                    Log.e("[Log.e] 친구요청리스트 불러오기 실패", " receiveFriendResponse is null");
                    mFriendAlarmActivityView.validateFailure(null);
                    return;
                }
                if(receiveFriendResponse.getResult() == null) {
                    Log.e("[Log.e] 친구요정리스트 불러오기 성공", " 친구가 없습니다");
                    mFriendAlarmActivityView.validateSuccess(receiveFriendResponse.getMessage());
                } else {
                    Log.e("[Log.e] 친구요정리스트 불러오기 성공", " 친구가 있음");
                    mFriendAlarmActivityView.getArrayReceive(receiveFriendResponse.getResult());
                }
            }

            @Override
            public void onFailure(Call<ReceiveFriendResponse> call, Throwable t) {
                Log.e("[Log.e] 친구요정리스트 불러오기 실패", " on Failure : " + t.getLocalizedMessage());
                mFriendAlarmActivityView.validateFailure(null);
            }
        });
    }

    public void acceptOk(RequestNumber number) {
        final FriendAlarmActivityRetrofitInterface friendAlarmActivityRetrofitInterface = getRetrofit().create(FriendAlarmActivityRetrofitInterface.class);
        friendAlarmActivityRetrofitInterface.acceptFriend(number).enqueue(new Callback<AddFriendResponse>() {
            @Override
            public void onResponse(Call<AddFriendResponse> call, Response<AddFriendResponse> response) {
                final AddFriendResponse addFriendResponse = response.body();
                if(addFriendResponse == null) {
                    Log.e("[Log.e] 친구 수락 실패", " addFriendResponse is null");
                    mFriendAlarmActivityView.validateFailure(null);
                    return;
                }
                Log.e("[Log.e] 친구 수락 성공", " ");
            }

            @Override
            public void onFailure(Call<AddFriendResponse> call, Throwable t) {
                Log.e("[Log.e] 친구 수락 실패", " on Failure : " + t.getLocalizedMessage());
                mFriendAlarmActivityView.validateFailure(null);
            }
        });
    }
}
