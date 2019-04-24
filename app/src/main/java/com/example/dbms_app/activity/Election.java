package com.example.dbms_app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dbms_app.R;
import com.example.dbms_app.model.Candidate;

import org.w3c.dom.Text;

public class Election extends AppCompatActivity {
    private TextView mLokSabhaNoOfConst,mLokSabhaStartDate,mLokSabhaEligibility,mLokSabhaType;
    private Spinner spinner;
    private Button button;
    private Class activityToOpen;
    String election_id = null,selection = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election);

        Bundle data = getIntent().getExtras();
        election_id = (String)data.get("election_id");
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

        spinner = (Spinner) findViewById(R.id.spinner);
        button= (Button) findViewById(R.id.btnSubmit);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.Locations,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new function());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityToOpen != null) {
                    Intent intent = new Intent(view.getContext(), activityToOpen);
                    intent.putExtra("id",election_id);
                    intent.putExtra("region",selection);
                    startActivity(intent);
                }
            }
        });


    }

    public class function implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View arg1, int pos,
                                   long id) {
            selection = ((TextView) arg1).getText().toString();
            activityToOpen = CandidateActivity.class;
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}
