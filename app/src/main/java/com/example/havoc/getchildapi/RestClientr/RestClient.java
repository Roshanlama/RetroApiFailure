package com.example.havoc.getchildapi.RestClientr;

import com.example.havoc.getchildapi.model.GetClassResponse;
import com.example.havoc.getchildapi.model.LoginPojo;
import com.example.havoc.getchildapi.model.SystemInfoPojo;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by havoc on 8/21/17.
 */

public class RestClient {
    private static RetroApiInterface retroApiInterface;
    private static String baseUrl = "http://5.189.171.144/site/school/";

    public static RetroApiInterface getClient() {
        if (retroApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.setConnectTimeout(5, TimeUnit.MINUTES);
            okClient.setReadTimeout(5, TimeUnit.MINUTES);
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retroApiInterface = client.create(RetroApiInterface.class);
        }
        return retroApiInterface;
    }

    public interface RetroApiInterface {
        @FormUrlEncoded
        @POST("index.php?mobile/login")
        Call<LoginPojo> apiLogin(@Field("email") String username, @Field("password") String password, @Field("authenticate") String authenticate);

        @FormUrlEncoded
        @POST("index.php?mobile/get_system_info")
        Call<SystemInfoPojo> apiSystemInfo(@Field("authenticate") String authenticate);


        @FormUrlEncoded
        @POST("index.php?mobile/get_class")
        Call<GetClassResponse> apiGetchild(@Field("authentication_key") String authKey, @Field("user_type") String usertype);
    }
}
