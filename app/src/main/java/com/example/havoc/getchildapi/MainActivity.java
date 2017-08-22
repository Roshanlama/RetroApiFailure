package com.example.havoc.getchildapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.havoc.getchildapi.RestClientr.RestClient;
import com.example.havoc.getchildapi.model.GetClassResponse;

import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    String authKey, loginType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences= getApplicationContext().getSharedPreferences("DGSchool", 0);
        authKey= preferences.getString("AUTHKEY", "");
        loginType= preferences.getString("LOGINTYPE", "");
        callAPi();
    }

    private void callAPi() {
        RestClient.RetroApiInterface retro = RestClient.getClient();
        retro.apiGetchild(authKey,loginType)
                .enqueue(new Callback<GetClassResponse>() {
                    @Override
                    public void onResponse(Response<GetClassResponse> response) {
                        if (response!=null){
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }else if (response==null){
                            Toast.makeText(MainActivity.this, "Response is null", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, "Api not called", Toast.LENGTH_SHORT).show();

                    }
                });
               /* .enqueue(new Callback<List<GetClassResponse>>() {
                    @Override
                    public void onResponse(Response<List<GetClassResponse>> response) {
                        if (response!=null){

                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }else if (response==null){
                            Toast.makeText(MainActivity.this, "Response is null", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, "Api Not Called", Toast.LENGTH_SHORT).show();
                    }
                });*/
                /*.enqueue(new Callback<GetClassResponse>() {
                    @Override
                    public void onResponse(Response<GetClassResponse> response) {
                        if (response!=null){
                            List<GetClassPojo> classpojo = response.body().getData();
                            for (int i =0; i<classpojo.size(); i++){
                                username = classpojo.get(i).getName();


                            }
                            Toast.makeText(MainActivity.this, "Username" + username, Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }else if (response==null){
                            Toast.makeText(MainActivity.this, "Response is null", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(MainActivity.this, "APi not called", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }
}
