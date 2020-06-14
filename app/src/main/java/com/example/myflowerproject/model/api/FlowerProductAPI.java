package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.results.CategoryResult;
import com.example.myflowerproject.model.results.SearchByNameResult;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlowerProductAPI {
    @GET("/flower-products/find-by-name")
    Call<SearchByNameResult> getFlowersByName(
            @Header("Authorization") String auth,
            @Query("key") String name
    );
}
