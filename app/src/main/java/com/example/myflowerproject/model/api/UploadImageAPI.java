package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.DataSignupResult;
import com.example.myflowerproject.model.results.UserLoginResult;
import com.example.myflowerproject.model.results.UserResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UploadImageAPI {
    @Multipart
    @POST("/public/upload")
    Call<String> postImage(@Part MultipartBody.Part file);


}
