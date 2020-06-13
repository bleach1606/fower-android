package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.Notification;
import com.example.myflowerproject.model.results.NotificationResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface NotificationAPI {
    @GET("/notification")
    Call<NotificationResult> getNotification(@Header("Authorization") String auth);

    @PUT("/notification/seen")
    Call<Notification> seenNotification(
            @Header("Authorization") String auth,
            @Query("id") int id
    );
}
