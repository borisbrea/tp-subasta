package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.AccountRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class PutPaymentMethodAccountDao extends AsyncTask<AccountRequest, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(AccountRequest... AccountRequests) {
        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.putPaymentMethodAccount("https://auction-api-rest.herokuapp.com/users/" + 1 + "/payment_methods", AccountRequests[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
