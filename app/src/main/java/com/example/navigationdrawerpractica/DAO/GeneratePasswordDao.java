package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.GeneratePasswordRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneratePasswordDao extends AsyncTask<String, Void, Integer> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();
    Integer response = 0;

    @Override
    protected Integer doInBackground(String... strings) {
        if(strings != null){
            String blr = strings[0];
        }


        return null;
    }
}
