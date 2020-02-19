package com.softsquared.runtastic.src.login;

import android.util.Log;

import com.softsquared.runtastic.src.login.interfaces.LoginActivityView;
import com.softsquared.runtastic.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.runtastic.src.login.models.LoginRequest;
import com.softsquared.runtastic.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postLogin(LoginRequest requestBody) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postLogin(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if(loginResponse == null){
                    Log.e("[Log.e} tag","로그인 실패");
                    mLoginActivityView.validateFailure(null);
                    return;
                }
                mLoginActivityView.validateSuccess(loginResponse.getMessage());
                mLoginActivityView.validateCode(loginResponse.getCode());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("[Log.e} tag", "로그인 실패(onFailure)");

                mLoginActivityView.validateFailure(null);
            }
        });
    }
}
