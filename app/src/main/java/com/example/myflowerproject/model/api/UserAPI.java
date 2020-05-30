package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserLoginResult;
import com.example.myflowerproject.model.results.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("/public/login")
    Call<UserLoginResult> postLogin(@Body Users user);

    @POST("/public/sigup")
    Call<UserResult> signup(@Body Users user);

    @POST("/users/update-fcm")
    Call<UserResult> updateTokenFCM(@Field("token") String token);

    @POST("/users/update")
    Call<UserResult> updateUser(@Body Users user);
}
