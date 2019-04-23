package com.example.dbms_app.network;

import com.example.dbms_app.model.BoolCheck;
import com.example.dbms_app.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GetUserDataService {
    @POST("user")
    Call<User> getUserCheck(@Body User body);
}

