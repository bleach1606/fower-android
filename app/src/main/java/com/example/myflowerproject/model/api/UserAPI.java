package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.dao.ForgotPassword;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.DataSignupResult;
import com.example.myflowerproject.model.results.UserLoginResult;
import com.example.myflowerproject.model.results.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserAPI {

    @POST("/public/login")
    Call<UserLoginResult> postLogin(@Body Users user);

    @POST("/public/login-access")
    Call<UserLoginResult> postLoginFace(@Body Users user);

    @POST("/public/signup")
    Call<DataSignupResult> signup(@Body Users user);

    @FormUrlEncoded
    @POST("/users/update-fcm")
    Call<UserResult> updateTokenFCM(
            @Header("Authorization") String auth,
            @Field("token") String token
    );

    @GET("/public/forget-password")
    Call<UserResult> resetPassword(
            @Query("username") String username
    );

    @POST("/public/forget-password")
    Call<UserResult> resetPasswordWithCode(@Body ForgotPassword forgotPassword);

    @PUT("/users/update")
    Call<UserResult> updateUser(
            @Header("Authorization") String auth,
            @Body Users user
    );
}
