package com.softsquared.runtastic.src.main.fragment.Status.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.Status.interfaces.AddShoes3StepRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.AddShoes3StepView;
import com.softsquared.runtastic.src.main.fragment.Status.models.SneakersRequest;
import com.softsquared.runtastic.src.main.fragment.Status.models.SneakersResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class AddShoes3StepService {
    final AddShoes3StepView mAddShoes3StepView;

    public AddShoes3StepService(AddShoes3StepView addShoes3StepView) {
        this.mAddShoes3StepView = addShoes3StepView;
    }

    public void postSneakers(SneakersRequest request) {
        final AddShoes3StepRetrofitInterface addShoes3StepRetrofitInterface = getRetrofit().create(AddShoes3StepRetrofitInterface.class);
        addShoes3StepRetrofitInterface.postSneakersInfo(request).enqueue(new Callback<SneakersResponse>() {
            @Override
            public void onResponse(Call<SneakersResponse> call, Response<SneakersResponse> response) {
                final SneakersResponse sneakersResponse = response.body();
                if(sneakersResponse == null) {
                    Log.e("[Log.e] 운동화 추가 실패", "sneakersResponse is null");
                    mAddShoes3StepView.validateFailure(null);
                    return;
                }
                if(sneakersResponse.getCode() == 100) {
                    Log.e("[Log.e] 운동화 추가 성공", sneakersResponse.getMessage());
                    mAddShoes3StepView.validateSuccess(sneakersResponse.getMessage());
                } else {
                    Log.e("[Log.e] 운동화 추가 실패", "code is " + sneakersResponse.getCode() + " ");
                    mAddShoes3StepView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<SneakersResponse> call, Throwable t) {
                Log.e("[Log.e] 운동화 추가 실패", "onFailure" + t.getLocalizedMessage());
                mAddShoes3StepView.validateFailure(null);
            }
        });
    }
}
