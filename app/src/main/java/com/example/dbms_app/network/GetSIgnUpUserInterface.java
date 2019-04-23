package com.example.dbms_app.network;

import com.example.dbms_app.model.BoolCheck;
import com.example.dbms_app.model.SignUpUser;
import com.example.dbms_app.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface GetSIgnUpUserInterface {
    @PUT("user")
    Call<BoolCheck> getUserCheck(@Body SignUpUser body);
}
