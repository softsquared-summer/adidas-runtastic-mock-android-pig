package com.softsquared.runtastic.src.main.fragment.Status.services;

import android.util.Log;

import com.softsquared.runtastic.src.main.fragment.Status.interfaces.SearchShoesActivityRetrofitInterface;
import com.softsquared.runtastic.src.main.fragment.Status.interfaces.SearchShoesActivityView;
import com.softsquared.runtastic.src.main.fragment.Status.models.BrandResponse;
import com.softsquared.runtastic.src.main.fragment.Status.models.ModelsResponse;

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
                    mSearchShoesActivityView.hideLoading();
                    return;
                }
                Log.e("[Log.e] 브랜드 가져오기 성공", "성공 ");
                mSearchShoesActivityView.getArrayBrand(brandResponse.getResult());
            }

            @Override
            public void onFailure(Call<BrandResponse> call, Throwable t) {
                Log.e("[Log.e] 브랜드 가져오기 실패","onFailure : " + t.getLocalizedMessage());
                mSearchShoesActivityView.hideLoading();
            }
        });
    }

    public void getArrayModel(String brandNo) {
        final SearchShoesActivityRetrofitInterface searchShoesActivityRetrofitInterface = getRetrofit().create(SearchShoesActivityRetrofitInterface.class);
        searchShoesActivityRetrofitInterface.searchModelsByBrand(brandNo).enqueue(new Callback<ModelsResponse>() {
            @Override
            public void onResponse(Call<ModelsResponse> call, Response<ModelsResponse> response) {
                final ModelsResponse modelsResponse = response.body();
                if(modelsResponse == null) {
                    Log.e("[Log.e] 모델 가져오기 실패", "brandResponse is null");
                    mSearchShoesActivityView.hideLoading();
                    return;
                }
                Log.e("[Log.e] 모델 가져오기 성공", "성공 ");
                mSearchShoesActivityView.getArrayModel(modelsResponse.getResult());
            }

            @Override
            public void onFailure(Call<ModelsResponse> call, Throwable t) {
                Log.e("[Log.e] 모델 가져오기 실패","onFailure : " + t.getLocalizedMessage());
                mSearchShoesActivityView.hideLoading();
            }
        });
    }
}
