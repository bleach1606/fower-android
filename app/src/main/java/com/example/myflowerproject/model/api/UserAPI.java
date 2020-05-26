package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserLoginResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/public/login")
    Call<UserLoginResult> postLogin(@Body Users user);

    @POST("/public/sigup")
    Call<UserLoginResult> signup(@Body Users user);


}
