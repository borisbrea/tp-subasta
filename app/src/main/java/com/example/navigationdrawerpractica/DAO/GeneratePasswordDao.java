package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.GeneratePasswordRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneratePasswordDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();
    Integer response = 0;

    @Override
    protected Response doInBackground(String... strings) {

         GeneratePasswordRequest request = new GeneratePasswordRequest(strings[0], strings[1],strings[2]);
         Response<Void> response = null;

            apiService = RetrofitClient.getApiService();
            try {
                response = apiService.generatePassword(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return response;
    }
}
