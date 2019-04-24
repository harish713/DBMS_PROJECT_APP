package com.example.dbms_app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbms_app.R;
import com.example.dbms_app.model.User;
import com.example.dbms_app.network.GetElectionsDataService;
import com.example.dbms_app.network.GetUserDataService;
import com.example.dbms_app.network.RetrofitClientInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private TextView signupLink;
    private static final int REQUEST_SIGNUP = 0;
    private Button electionsB;
    private Button candidatesB;
    private RadioGroup option;
    private RadioButton choice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signupLink = (TextView) findViewById(R.id.link_signup);
        loginButton = (Button) findViewById(R.id.btn_login);
        passwordText = (EditText) findViewById(R.id.input_password);
        emailText = (EditText) findViewById(R.id.input_email);
        option = (RadioGroup)findViewById(R.id.option);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    login();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() throws JSONException {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        System.out.println(email);
        // TODO: Implement your own authentication logic here.
        GetUserDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetUserDataService.class);
        Call<User> call = service.getUserCheck(new User(email,password,0));

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    User data = response.body();
                    if(data.getResult() == 0)
                    {
                        Log.d("SUCCESS",data.getUsername());
                        Toast.makeText(MainActivity.this,"NOT REGISTERED",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Log.d("SUCCESS","AA GAYA");
                        Toast.makeText(MainActivity.this,"LOGGED IN",Toast.LENGTH_LONG).show();

                        int selectedId = option.getCheckedRadioButtonId();
                        choice = (RadioButton) findViewById(selectedId);

                        if(((String)choice.getText()).compareTo("Elections") == 0){
                            Intent i = new Intent(MainActivity.this,ElectionDetails.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"INSIDE CANDIDATES",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("FAIL",t.getMessage().toString());
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

//        String email = emailText.getText().toString();
//        String password = passwordText.getText().toString();
//
//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            emailText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            emailText.setError(null);
//        }
//
//        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
//            passwordText.setError("between 4 and 10 alphanumeric characters");
//            valid = false;
//        } else {
//            passwordText.setError(null);
//        }

        return valid;
    }

}

