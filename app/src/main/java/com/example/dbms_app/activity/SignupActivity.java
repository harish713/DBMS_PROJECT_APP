package com.example.dbms_app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms_app.R;
import com.example.dbms_app.model.BoolCheck;
import com.example.dbms_app.model.SignUpUser;
import com.example.dbms_app.model.User;
import com.example.dbms_app.network.GetSIgnUpUserInterface;
import com.example.dbms_app.network.GetUserDataService;
import com.example.dbms_app.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    private EditText mName;
    private EditText mAadhar;
    private EditText mPassword;
    private Button mSignUpButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mName = (EditText)findViewById(R.id.input_name);
        mAadhar = (EditText)findViewById(R.id.input_aadhar);
        mPassword = (EditText)findViewById(R.id.input_password);
        mSignUpButton = (Button)findViewById(R.id.btn_signup);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });
    }

    public void addUser(){
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        String aadhaar = mAadhar.getText().toString();
        System.out.println(name);
        System.out.println(password);
        System.out.println(aadhaar);
        // TODO: Implement your own authentication logic here.
        GetSIgnUpUserInterface service = RetrofitClientInstance.getRetrofitInstance().create(GetSIgnUpUserInterface.class);
        Call<BoolCheck> call = service.getUserCheck(new SignUpUser(name,password,aadhaar,0));

        call.enqueue(new Callback<BoolCheck>() {
            @Override
            public void onResponse(Call<BoolCheck> call, Response<BoolCheck> response) {
                if(response.isSuccessful()){
                    BoolCheck data = response.body();
                    if(data.getRes() == 0)
                    {
                        Log.d("SUCCESS","NAHI AAYA");
                        Toast.makeText(SignupActivity.this,"REGISTRATION UNSUCCESSFUL",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.d("SUCCESS","AA GAYA");
                        Toast.makeText(SignupActivity.this,"SUCCESSFULLY SIGNED UP",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SignupActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(Call<BoolCheck> call, Throwable t) {
                Log.d("FAIL",t.getMessage().toString());
            }
        });
    }
}
