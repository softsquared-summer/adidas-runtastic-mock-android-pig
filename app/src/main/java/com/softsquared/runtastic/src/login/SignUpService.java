package com.softsquared.runtastic.src.login;

import android.util.Log;

import com.softsquared.runtastic.src.login.interfaces.SignUpActivityView;
import com.softsquared.runtastic.src.login.interfaces.SignUpRetrofitInterface;
import com.softsquared.runtastic.src.login.models.SignUpRequest;
import com.softsquared.runtastic.src.login.models.SignUpResponse;
import com.softsquared.runtastic.src.main.models.DefaultResponse;

import okhttp3.RequestBody;
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
                mSignUpActivityView.validateSuccess(signUpResponse.getMessage());
                mSignUpActivityView.validateCode(signUpResponse.getCode());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure(null);
                Log.e("[Log.e} tag","회원가입 실패2");
            }
        });
    }
}
