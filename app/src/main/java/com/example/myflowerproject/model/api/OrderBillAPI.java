package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.OrderBill;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserLoginResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderBillAPI {

    @GET("/orderbill/get-current-order")
    Call<OrderBill> getCurrentOrder(@Header("Authorization") String auth);

    @GET("/orderbill/get-order-list")
    Call<OrderBill> getOrderList(@Header("Authorization") String auth);

    @POST("/category/create")
    Call<OrderBill> createOrderBill(@Header("Authorization") String auth);
}
