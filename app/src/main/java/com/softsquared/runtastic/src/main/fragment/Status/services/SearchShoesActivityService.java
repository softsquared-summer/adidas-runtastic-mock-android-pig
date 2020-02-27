package com.softsquared.runtastic.src.main.fragment.Status.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.Status.interfaces.SearchShoesActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.SearchShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.models.BrandResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.runtastic.src.ApplicationClass.getRetrofit;

public class SearchShoesActivityService {
    final SearchShoesActivityView mSearchShoesActivityView;

    public SearchShoesActivityService(SearchShoesActivityView searchShoesActivityView) {
        this.mSearchShoesActivityView = searchShoesActivityView;
    }

    public void getArrayBrand() {
        final SearchShoesActivityRetrofitInterface searchShoesActivityRetrofitInterface = getRetrofit().create(SearchShoesActivityRetrofitInterface.class);
        searchShoesActivityRetrofitInterface.searchSneakersBrand().enqueue(new Callback<BrandResponse>() {
            @Override
            public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {
                final BrandResponse brandResponse = response.body();
                if(brandResponse == null) {
                    Log.e("[Log.e] 브랜드 가져오기 실패", "brandResponse is null");
                    return;
                }
                Log.e("[Log.e] 브랜드 가져오기 성공", "성공 ");
                mSearchShoesActivityView.getArrayBrand(brandResponse.getResult());
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                Log.e("[Log.e] 브랜드 가져오기 실패","onFailure : " + t.getLocalizedMessage());
            }
        });
    }
}
