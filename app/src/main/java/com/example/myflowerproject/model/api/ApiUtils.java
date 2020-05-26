package com.example.myflowerproject.model.api;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.1.42:8080/";

    public static UserAPI getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(UserAPI.class);
    }
}
