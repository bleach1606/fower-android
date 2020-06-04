package com.example.myflowerproject.model.api;

import com.example.myflowerproject.model.entity.OrderBill;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.1.25:8080/";

    public static UserAPI getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(UserAPI.class);
    }

    public static CategoryAPI getCategoryAPI() {

        return RetrofitClient.getClient(BASE_URL).create(CategoryAPI.class);
    }

    public static OrderBillAPI getOrderBillAPI() {
        return RetrofitClient.getClient(BASE_URL).create(OrderBillAPI.class);
    }
}
