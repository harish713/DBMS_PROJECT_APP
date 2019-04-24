package com.example.dbms_app.network;

import com.example.dbms_app.model.Candidate;
import com.example.dbms_app.model.RegionWiseDetailPair;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface GetCandidateDataInterface {
    @PUT("candidate-region")
    Call<List<Candidate>> getCandidateData(@Body RegionWiseDetailPair body);
}
