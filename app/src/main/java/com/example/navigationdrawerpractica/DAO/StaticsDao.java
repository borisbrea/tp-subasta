package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.BidResponse;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.StaticsResponse;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class StaticsDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(String... strings) {
        Response <StaticsResponse> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getStatics("https://auction-api-rest.herokuapp.com/users/" + strings[0] + "/stats").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}