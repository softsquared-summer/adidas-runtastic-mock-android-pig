package com.softsquared.runtastic.src.main.fragment.Profile;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.Profile.interfaces.FragmentProfileRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.Profile.interfaces.FragmentProfileView;
import com.softsquared.runtastic.src.main.fragment.Profile.models.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class FragmentProfileService {
    private final FragmentProfileView mFragmentProfileView;

    FragmentProfileService(final FragmentProfileView fragmentProfileView) {
        this.mFragmentProfileView = fragmentProfileView;
    }

    void getProfile() {
        final FragmentProfileRetrofitInterface fragmentProfileRetrofitInterface = getRetrofit().create(FragmentProfileRetrofitInterface.class);
        fragmentProfileRetrofitInterface.getProfileTab().enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                final ProfileResponse profileResponse = response.body();
                if(response.isSuccessful()) Log.e("getProfile","response.isSuccessful()");
                else  Log.e("getProfile","response.isNotSuccessful() : " + response.code());
                if (profileResponse == null) {
                    mFragmentProfileView.validateFailure(null);
                    Log.e("[Log.e} getProfile","프로필탭 불러오기 실패");
                    return;
                }
                if (profileResponse.getCode() == 100) {
                    Log.e("[Log.e} getProfile","프로필탭 불러오기 성공");
                    mFragmentProfileView.getProfileResult(profileResponse.getResult());

                } else {
                    Log.e("[Log.e} getProfile","프로필탭 불러오기 실패");
                    mFragmentProfileView.validateFailure(null);
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                mFragmentProfileView.validateFailure(null);
                Log.e("[Log.e} getProfile","프로필탭 불러오기 실패(on Failure) : " + t.getLocalizedMessage());
            }
        });
    }
}

