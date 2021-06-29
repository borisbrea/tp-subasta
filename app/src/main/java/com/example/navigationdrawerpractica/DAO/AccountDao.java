package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.AccountResponse;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class AccountDao extends AsyncTask<Integer, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(Integer... integers) {
        Response<AccountResponse> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getAAccountData("https://auction-api-rest.herokuapp.com/users/" + 1).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
