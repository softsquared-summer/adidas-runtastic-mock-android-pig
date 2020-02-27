package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.models.BrandResponse;
import com.softsquared.runtastic.src.main.fragment.Status.models.ModelsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface SearchShoesActivityRetrofitInterface {
    @GET("/sneakers/brand")
    Call<BrandResponse> searchSneakersBrand();

    @GET("/sneakers/model/{brandNo}")
    Call<ModelsResponse> searchModelsByBrand(@Path("brandNo") String brand);
}
