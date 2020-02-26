package com.softsquared.runtastic.src.sign;

import android.util.Log;

import com.softsquared.runtastic.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.runtastic.src.login.models.LoginRequest;
import com.softsquared.runtastic.src.login.models.LoginResponse;
import com.softsquared.runtastic.src.sign.interfaces.SignUpActivityView;
import com.softsquared.runtastic.src.sign.interfaces.SignUpRetrofitInterface;
import com.softsquared.runtastic.src.sign.models.SignUpRequest;
import com.softsquared.runtastic.src.sign.models.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class SignUpService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpService(final SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }

    void postSignUp(SignUpRequest requestBody) {
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.postSIgnUp(requestBody).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if(signUpResponse == null){
                    Log.e("[Log.e} tag","회원가입 실패1");
                    mSignUpActivityView.validateFailure(null);
                    return;
                }
//                mSignUpActivityView.validateSuccess(signUpResponse.getMessage());
                mSignUpActivityView.validateCode(signUpResponse.getCode(),signUpResponse.getUserNo());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure(null);
                Log.e("[Log.e} tag","회원가입 실패2" + t.getLocalizedMessage());
            }
        });
    }

    void postLogin(LoginRequest requestBody) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postLogin(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if(loginResponse == null){
                    Log.e("[Log.e} tag","jwt 발급 실패");
                    mSignUpActivityView.validateFailure(null);
                    return;
                }
                mSignUpActivityView.validateSuccess(loginResponse.getMessage());
                mSignUpActivityView.putJwtToken(loginResponse.getResult().getJwt());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("[Log.e} tag", "jwt 발급 실패(onFailure)");

                mSignUpActivityView.validateFailure(null);
            }
        });
    }
}
