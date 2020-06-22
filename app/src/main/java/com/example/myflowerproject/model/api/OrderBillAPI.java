package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.OrderBill;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.ListOrderBillResult;
import com.example.myflowerproject.model.results.OrderBillResult;
import com.example.myflowerproject.model.results.UserLoginResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OrderBillAPI {

    @GET("/orderbill/get-current-order")
    Call<OrderBillResult> getCurrentOrder(@Header("Authorization") String auth);

    @GET("/orderbill/get-order-list")
    Call<ListOrderBillResult> getOrderList(@Header("Authorization") String auth);

    @POST("/orderbill/update-orderBill")
    Call<OrderBillResult> updateOrderBill(
            @Header("Authorization") String auth,
            @Body OrderBill orderBill
    );

}