package com.example.dbms_app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms_app.R;
import com.example.dbms_app.model.BoolCheck;
import com.example.dbms_app.model.Election;
import com.example.dbms_app.network.GetElectionsDataService;
import com.example.dbms_app.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewElection extends AppCompatActivity {
    EditText txtType,txtNoOfConst,txtDate,txtEligibility;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_new_election);
                    txtType = (EditText) findViewById(R.id.ed_type);
                    txtNoOfConst = (EditText) findViewById(R.id.ed_no_of_const);
                    txtDate = (EditText) findViewById(R.id.ed_date);
                    txtEligibility = (EditText) findViewById(R.id.ed_eligibility);

                    bt = (Button)findViewById(R.id.button);
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String type = txtType.getText().toString();
                            int no_of_const = Integer.parseInt(txtNoOfConst.getText().toString());
                            String date = txtDate.getText().toString();
                            int eligibility = Integer.parseInt(txtEligibility.getText().toString());

                            Election e = new Election(type,no_of_const,date,eligibility);
                GetElectionsDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetElectionsDataService.class);
                Call<BoolCheck> call = service.insertElection(new Election(type,no_of_const,date,eligibility));

                call.enqueue(new Callback<BoolCheck>() {
                    @Override
                    public void onResponse(Call<BoolCheck> call, Response<BoolCheck> response) {
                        BoolCheck data = response.body();
                        if(data.getRes() == 1){
                            Log.d("success", "aa gaya");
                            Toast.makeText(NewElection.this,"INSERTED",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(NewElection.this,ElectionDetails.class);
                            startActivity(i);
                        }
                        else{
                            Log.d("success", "aa gaya");
                            Toast.makeText(NewElection.this,"INSERTED",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(NewElection.this,ElectionDetails.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<BoolCheck> call, Throwable t) {
                        Log.d("FULLY FAILED", "nhi aaya");
                    }
                });

            }
        });
    }

}
