package com.example.havoc.getchildapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.havoc.getchildapi.RestClientr.RestClient;
import com.example.havoc.getchildapi.model.LoginPojo;
import com.example.havoc.getchildapi.model.SystemInfoPojo;

import retrofit.Callback;
import retrofit.Response;

public class LoginActivity extends AppCompatActivity {

    EditText mLogin, mPassword, mAuthenticate;
    Button mLoginButton, mForgotPassword;
    String authenticate= "false";
    TextView mSystemName;
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Checking if the user is already logged in and skipping the login process through Shared Preferences//
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("DGSchoolPrefs", 0);
        if (preferences.getString("USERID", null)!=null){
            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initValues();
    }

    private void initValues() {
         mSystemName=(TextView)findViewById(R.id.app_name_textView);
        mLogin= (EditText)findViewById(R.id.email_editText);
        mPassword=(EditText)findViewById(R.id.password_editText);
        mLoginButton=(Button)findViewById(R.id.login_button);
        mForgotPassword=(Button)findViewById(R.id.forgot_password_button);

        //Getting the System name first to display in Login Page//
        callSystemInfo();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callLogin();
            }
        });

        mForgotPassword=(Button)findViewById(R.id.forgot_password_button);
        mForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

    }

    
    //Getting the School Name//
    private void callSystemInfo() {
        RestClient.RetroApiInterface retroApiInterface= RestClient.getClient();

        retroApiInterface.apiSystemInfo(authenticate)
                .enqueue(new Callback<SystemInfoPojo>() {
                    @Override
                    public void onResponse(Response<SystemInfoPojo> response) {
                        if (response.body()!= null){
                           String name = response.body().getSystemName();
                            mSystemName.setText(name);

                        }
                        else if (response.body()==null){
                            Toast.makeText(LoginActivity.this, "System Error", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

    //Getting the Login Details//
    private void callLogin() {
        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mProgressDialog.setMessage("Logging in");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        RestClient.RetroApiInterface retroApiInterface= RestClient.getClient();

        retroApiInterface.apiLogin(mLogin.getText().toString().trim(), mPassword.getText().toString().trim(), authenticate.toString().trim())
                .enqueue(new Callback<LoginPojo>() {
                    @Override
                    public void onResponse(Response<LoginPojo> response) {
                        mProgressDialog.dismiss();
                        if (response.body()!=null){

                            if (response.body().getStatus().equals("success")){

                                String loginType = response.body().getLoginType();
                                String loginUserId= response.body().getLoginUserId();
                                String userName= response.body().getName();
                                String authKey= response.body().getAuthenticationKey();

                                //Storing the login details to skip the login process if already logged in//
                                SharedPreferences preferences = getApplicationContext().getSharedPreferences("DGSchool", 0);
                                SharedPreferences.Editor editor = preferences.edit();

                                //Storing login details for further use in respective activity//
                                editor.putString("LOGINTYPE", loginType);
                                editor.putString("USERID", loginUserId);
                                editor.putString("USERNAME", userName);
                                editor.putString("AUTHKEY", authKey);

                                //Getting and Storing extra information of user, if the user is Student//
                                if (loginType.equals("student")){

                                    editor.putString("CLASSID", response.body().getClassID());
                                }
                                editor.apply();
                                Intent homeIntent= new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(homeIntent);
                            }
                        }
                        else if (response.body()==null){

                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mProgressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}
