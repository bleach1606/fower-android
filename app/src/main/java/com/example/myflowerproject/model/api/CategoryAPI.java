package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.results.CategoryResult;
import com.example.myflowerproject.model.results.UserLoginResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CategoryAPI {
    @GET("/category/find-all")
    Call<CategoryResult> findCategory(
            @Header("Authorization") String auth
    );

    @GET("/category/find-by-id/{id}")
    Call<CategoryResult> getCategoryById(
            @Header("Authorization") String auth,
            @Path("id") int id
    );
}
