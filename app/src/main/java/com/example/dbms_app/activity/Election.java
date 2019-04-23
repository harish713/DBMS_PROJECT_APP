package com.example.dbms_app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.dbms_app.R;

import org.w3c.dom.Text;

public class Election extends AppCompatActivity {
    private TextView mLokSabhaNoOfConst,mLokSabhaStartDate,mLokSabhaEligibility,mLokSabhaType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);

        Bundle data = getIntent().getExtras();
        com.example.dbms_app.model.Election election = (com.example.dbms_app.model.Election) data.getParcelable("singleElection");
        mLokSabhaStartDate = (TextView)findViewById(R.id.lok_sabha_start_date);
        mLokSabhaNoOfConst = (TextView)findViewById(R.id.lok_sabha_no_of_const);
        mLokSabhaEligibility = (TextView)findViewById(R.id.lok_sabha_eligibility);
        mLokSabhaType = (TextView)findViewById(R.id.lok_sabha_type);
        mLokSabhaNoOfConst.setText(String.valueOf(election.getNoOfConst()));
        mLokSabhaEligibility.setText(String.valueOf(election.getEligibility()));
        if(election.getDates() != null){
        mLokSabhaStartDate.setText(election.getDates().substring(0,10));}
        else{
            mLokSabhaStartDate.setText("Undefined");
        }
        mLokSabhaType.setText(election.getType());
    }
}
