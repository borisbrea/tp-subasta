package com.example.navigationdrawerpractica.Cliente;

import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient  {

    public static final String   urlBase = "https://auction-api-rest.herokuapp.com/";
    private static      Retrofit retrofit;

    public static RetrofitApiService getApiService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(urlBase)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create((RetrofitApiService.class));
    }

}
