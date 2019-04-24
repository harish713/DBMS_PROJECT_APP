package com.example.dbms_app.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dbms_app.R;
import com.example.dbms_app.adapter.CandidateAdapter;
import com.example.dbms_app.adapter.ElectionAdapter;
import com.example.dbms_app.model.Candidate;
import com.example.dbms_app.model.RegionWiseDetailPair;
import com.example.dbms_app.network.GetCandidateDataInterface;
import com.example.dbms_app.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CandidateActivity extends AppCompatActivity {

    private CandidateAdapter adapter;
    private RecyclerView recyclerView;
    private static final String TAG = "CandidateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        Bundle data = getIntent().getExtras();
        String region = data.get("region").toString();
        int id = Integer.parseInt(data.get("id").toString());

        GetCandidateDataInterface service = RetrofitClientInstance.getRetrofitInstance().create(GetCandidateDataInterface.class);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_candidate_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CandidateActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //RETROFIT CODE
        Call<List<Candidate>> call = service.getCandidateData(new RegionWiseDetailPair(id,region));

        call.enqueue(new Callback<List<Candidate>>() {
            @Override
            public void onResponse(Call<List<Candidate>> call, Response<List<Candidate>> response) {
                if(response.isSuccessful()){
                    Log.d("CANDIDATE","HO GAYA");
                    List<Candidate> data = response.body();
                    for(Candidate c : data)
                    {
                        System.out.println(c.getCandidateName());
                    }
                    adapter = new CandidateAdapter((ArrayList<Candidate>) data);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new CandidateAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Log.d(TAG, "onItemClick position: " + position);
                            List<Candidate> data = adapter.getDataList();
                            Candidate c = data.get(position);
                            Toast.makeText(CandidateActivity.this,"CANDIDATE NAME :- " + c.getCandidateName() + "clicked",Toast.LENGTH_LONG).show();
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.d("SUCCESS BUT EMPTY","NO DATA");
                }
            }

            @Override
            public void onFailure(Call<List<Candidate>> call, Throwable t) {
                Log.d("FAIL",t.getMessage().toString());
            }
        });
    }
}
