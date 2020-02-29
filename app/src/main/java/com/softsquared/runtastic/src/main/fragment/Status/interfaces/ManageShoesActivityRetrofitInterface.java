package com.softsquared.runtastic.src.main.fragment.Status.interfaces;

import com.softsquared.runtastic.src.main.fragment.Status.models.ShoesDelRequest;
import com.softsquared.runtastic.src.main.fragment.Status.models.ShoesDelResponse;
import com.softsquared.runtastic.src.main.fragment.Status.models.UserShoesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;

public interface ManageShoesActivityRetrofitInterface {
    @GET("/user/sneakers")
    Call<UserShoesResponse> getUserSneakers();

    @HTTP(method = "DELETE", path = "/sneakers", hasBody = true)
    Call<ShoesDelResponse> deleteShoes(@Body ShoesDelRequest request);
}
