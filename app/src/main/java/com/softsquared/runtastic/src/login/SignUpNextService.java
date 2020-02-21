package com.softsquared.runtastic.src.login;

import android.util.Log;

import com.softsquared.runtastic.src.login.interfaces.SignUpActivityView;
import com.softsquared.runtastic.src.login.interfaces.SignUpNextActivityView;
import com.softsquared.runtastic.src.login.interfaces.SignUpNextRetrofitInterface;
import com.softsquared.runtastic.src.login.models.SetBodyRequest;
import com.softsquared.runtastic.src.login.models.SetBodyResponse;
import com.softsquared.runtastic.src.login.models.SetGoalRequest;
import com.softsquared.runtastic.src.login.models.SetGoalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class SignUpNextService {
    private final SignUpNextActivityView mSignUpNextActivityView;

    SignUpNextService (final SignUpNextActivityView signUpNextActivityView){
        this.mSignUpNextActivityView = signUpNextActivityView;
    }

    void tryPostSetBody(SetBodyRequest requestBody){
        final SignUpNextRetrofitInterface signUpNextRetrofitInterface = getRetrofit().create(SignUpNextRetrofitInterface.class);
        signUpNextRetrofitInterface.postBodyProfile(requestBody).enqueue(new Callback<SetBodyResponse>() {
            @Override
            public void onResponse(Call<SetBodyResponse> call, Response<SetBodyResponse> response) {
                final SetBodyResponse setBodyResponse = response.body();
                if(setBodyResponse == null) {
                    Log.e("[Log.e} tag","정보 추가 실패(Body)");
                    mSignUpNextActivityView.validateFailure(null);
                    return;
                }
                mSignUpNextActivityView.validateSuccess(setBodyResponse.getMessage(),setBodyResponse.getCode());
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
            }

            @Override
            public void onFailure(Call<SetGoalResponse> call, Throwable t) {
                Log.e("[Log.e} tag","정보 추가 실패(on Failure)(Goal)");
                mSignUpNextActivityView.validateFailure(null);
            }
        });
    }
}
