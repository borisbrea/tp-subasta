package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.home.Home;
import com.example.navigationdrawerpractica.Entidades.requestEntities.GeneratePasswordRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class AuctionHomeDao extends AsyncTask<Void, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(Void... voids) {
        Response<Home> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getSubastas().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
