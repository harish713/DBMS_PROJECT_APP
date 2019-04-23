package com.example.dbms_app.network;

import com.example.dbms_app.model.BoolCheck;
import com.example.dbms_app.model.Election;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetElectionsDataService {
    @GET("elections")
    Call<List<Election>> getElectionsData();
    @POST("elections")
    Call<BoolCheck> insertElection(@Body Election body);
}
