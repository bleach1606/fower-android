package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.results.UploadResult;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadImageAPI {

    @POST("public/upload")
    @Multipart
    Call<UploadResult> postImage(
            @Part MultipartBody.Part file
    );

}
