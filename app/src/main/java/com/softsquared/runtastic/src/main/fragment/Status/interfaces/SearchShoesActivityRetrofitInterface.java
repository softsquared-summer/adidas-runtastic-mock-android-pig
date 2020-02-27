package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.models.BrandResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchShoesActivityRetrofitInterface {
    @GET("/sneakers/brand")
    Call<BrandResponse> searchSneakersBrand();
}
