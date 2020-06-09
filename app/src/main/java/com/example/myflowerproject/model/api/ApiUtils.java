package com.example.myflowerproject.model.api;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.43.209:8080/";

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
