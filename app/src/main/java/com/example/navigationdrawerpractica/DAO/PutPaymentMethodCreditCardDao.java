package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.CreditCardRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class PutPaymentMethodCreditCardDao extends AsyncTask<CreditCardRequest, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(CreditCardRequest... CreditCardRequests) {
        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.putPaymentMethodCreditCard("https://auction-api-rest.herokuapp.com/users/" + 1 + "/payment_methods", CreditCardRequests[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
