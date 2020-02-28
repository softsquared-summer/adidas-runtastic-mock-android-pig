package com.softsquared.runtastic.src.main.fragment.Status.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.Status.interfaces.ManageShoesActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.ManageShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.models.UserShoesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class ManageShoesActivityService {
    final ManageShoesActivityView mManageShoesActivityView;

    public ManageShoesActivityService(ManageShoesActivityView manageShoesActivityView) {
        this.mManageShoesActivityView = manageShoesActivityView;
    }

    public void getArrayUserSneakers() {
        final ManageShoesActivityRetrofitInterface manageShoesActivityRetrofitInterface = getRetrofit().create(ManageShoesActivityRetrofitInterface.class);
        manageShoesActivityRetrofitInterface.getUserSneakers().enqueue(new Callback<UserShoesResponse>() {
            @Override
            public void onResponse(Call<UserShoesResponse> call, Response<UserShoesResponse> response) {
                final UserShoesResponse shoesResponse = response.body();
                if(shoesResponse == null) {
                    Log.e("[Log.e] 내 신발들 조회 실패", "shoesResponse is null");
                    mManageShoesActivityView.validateFailure(null);
                    return;
                }
                if(shoesResponse.getResult() == null) {
                    Log.e("[Log.e] 내 신발들 조회", "신발 없음");
                } else {
                    Log.e("[Log.e] 내 신발들 조회", "성공 ");
                    mManageShoesActivityView.getArrayShoes(shoesResponse.getResult());
                }
            }

            @Override
            public void onFailure(Call<UserShoesResponse> call, Throwable t) {
                Log.e("[Log.e] 내 신발들 조회 실패","onFailure : " + t.getLocalizedMessage());
                mManageShoesActivityView.validateFailure(null);
            }
        });
    }
}
