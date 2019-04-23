package com.example.dbms_app.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.dbms_app.R;
import com.example.dbms_app.adapter.ElectionAdapter;
import com.example.dbms_app.model.Election;
import com.example.dbms_app.network.GetElectionsDataService;
import com.example.dbms_app.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElectionDetails extends AppCompatActivity {

    private ElectionAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private static final String TAG = "ElectionDeltails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_details);
        Intent intent = getIntent();
        // RETROFIT CODE


        GetElectionsDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetElectionsDataService.class);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_election_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ElectionDetails.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //RETROFIT CODE
        Call<List<com.example.dbms_app.model.Election>> call = service.getElectionsData();

        call.enqueue(new Callback<List<Election>>() {
            @Override
            public void onResponse(Call<List<Election>> call, Response<List<Election>> response) {
                if(response.isSuccessful()){
                    Log.d("SUCCESS","HO GAYA");
                    List<Election> data = response.body();
                    adapter = new ElectionAdapter((ArrayList<Election>) data);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new ElectionAdapter.ClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            Log.d(TAG, "onItemClick position: " + position);
                            List<Election> data = adapter.getDataList();
                            Election e = data.get(position);
                            Intent i = new Intent(ElectionDetails.this, com.example.dbms_app.activity.Election.class);
                            i.putExtra("singleElection",e);
                            startActivity(i);
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
                else{
                    Log.d("SUCCESS BUT EMPTY","NO DATA");
                }
            }

            @Override
            public void onFailure(Call<List<Election>> call, Throwable t) {
                Log.d("FAIL",t.getMessage().toString());
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab_add) ;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ElectionDetails.this, NewElection.class));
            }
        });
    }
}
