package com.softsquared.runtastic.src.main.fragment.NewsPeed;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FriendAlarmActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.interfaces.FriendAlarmActivityView;
import com.softsquared.runtastic.src.main.fragment.NewsPeed.models.ReceiveFriendResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class FriendAlarmActivityService {
    final FriendAlarmActivityView mFriendAlarmActivityView;

    public FriendAlarmActivityService(FriendAlarmActivityView friendAlarmActivityView) {
        mFriendAlarmActivityView = friendAlarmActivityView;
    }

    void getReceiveList() {
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
                Log.e("[Log.e] 친구요정리스트 불러오기 성공", " ");
                if(receiveFriendResponse.getResult() == null) {
                    receiveFriendResponse.setMessage("받은 요청이 없습니다");
                    mFriendAlarmActivityView.validateSuccess(receiveFriendResponse.getMessage());
                } else {
                    mFriendAlarmActivityView.validateSuccess(receiveFriendResponse.getMessage());
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
}
