package com.example.navigationdrawerpractica.Cliente;

import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient  {

    public static final String   urlBase = "https://auction-api-rest.herokuapp.com/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(urlBase)
                    .addConverterFactory(GsonConverterFactory.create());

    private static      Retrofit retrofit = builder.build();;

    public static RetrofitApiService getApiService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(urlBase)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create((RetrofitApiService.class));
    }

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
