package com.softsquared.runtastic.src.sign;

import android.util.Log;

import com.softsquared.runtastic.src.sign.interfaces.SignUpNextActivityView;
import com.softsquared.runtastic.src.sign.interfaces.SignUpNextRetrofitInterface;
import com.softsquared.runtastic.src.sign.models.FcmRequest;
import com.softsquared.runtastic.src.sign.models.FcmResponse;
import com.softsquared.runtastic.src.sign.models.SetBodyRequest;
import com.softsquared.runtastic.src.sign.models.SetBodyResponse;
import com.softsquared.runtastic.src.sign.models.SetGoalRequest;
import com.softsquared.runtastic.src.sign.models.SetGoalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class SignUpNextService {
    private final SignUpNextActivityView mSignUpNextActivityView;

    SignUpNextService (final SignUpNextActivityView signUpNextActivityView){
        this.mSignUpNextActivityView = signUpNextActivityView;
    }

    void tryPuttSetBody(SetBodyRequest requestBody){
        final SignUpNextRetrofitInterface signUpNextRetrofitInterface = getRetrofit().create(SignUpNextRetrofitInterface.class);
        signUpNextRetrofitInterface.putBodyProfile(requestBody).enqueue(new Callback<SetBodyResponse>() {
            @Override
            public void onResponse(Call<SetBodyResponse> call, Response<SetBodyResponse> response) {
                final SetBodyResponse setBodyResponse = response.body();
                if(setBodyResponse == null) {
                    Log.e("[Log.e} tag","정보 추가 실패(Body)");
                    mSignUpNextActivityView.validateFailure(null);
                    return;
                }
                mSignUpNextActivityView.validateSuccess(setBodyResponse.getMessage(),setBodyResponse.getCode());
                mSignUpNextActivityView.setBodySuccess(setBodyResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SetBodyResponse> call, Throwable t) {
                Log.e("[Log.e} tag","정보 추가 실패(on Failure)(Body) : " + t.toString());
                mSignUpNextActivityView.validateFailure(null);
            }
        });
    }
    void tryPostSetGoal(SetGoalRequest requestBody){
        final SignUpNextRetrofitInterface signUpNextRetrofitInterface = getRetrofit().create(SignUpNextRetrofitInterface.class);
        signUpNextRetrofitInterface.postGoalProfile(requestBody).enqueue(new Callback<SetGoalResponse>() {
            @Override
            public void onResponse(Call<SetGoalResponse> call, Response<SetGoalResponse> response) {
                final SetGoalResponse setGoalResponse = response.body();
                if(setGoalResponse == null) {
                    Log.e("[Log.e} tag","정보 추가 실패(Goal)");
                    mSignUpNextActivityView.validateFailure(null);
                    return;
                }
                mSignUpNextActivityView.validateSuccess(setGoalResponse.getMessage(),setGoalResponse.getCode());
                mSignUpNextActivityView.setGoalSuccess(setGoalResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SetGoalResponse> call, Throwable t) {
                Log.e("[Log.e} tag","정보 추가 실패(on Failure)(Goal)");
                mSignUpNextActivityView.validateFailure(null);
            }
        });
    }

    void tryPostFcmToken(FcmRequest request) {
        final SignUpNextRetrofitInterface signUpNextRetrofitInterface = getRetrofit().create(SignUpNextRetrofitInterface.class);
        signUpNextRetrofitInterface.postFcmToken(request).enqueue(new Callback<FcmResponse>() {
            @Override
            public void onResponse(Call<FcmResponse> call, Response<FcmResponse> response) {
                final  FcmResponse fcmResponse = response.body();
                if (fcmResponse == null) {
                    Log.e("[Log.e} tag","fcm 토큰 보내기 실패 response is null");
                    return;
                }
                if(fcmResponse.getCode() == 100) {
                    Log.e("[Log.e} tag","fcm 토큰 보내기 성공");
                } else {
                    Log.e("[Log.e} tag","fcm 토큰 보내기 실패");
                }
            }

            @Override
            public void onFailure(Call<FcmResponse> call, Throwable t) {
                Log.e("[Log.e} tag","fcm 토큰 보내기 실패 onFailure" + t.getLocalizedMessage());
            }
        });
    }
}
