package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.BidRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class DeletePaymentMethodDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(String... strings) {
        BidRequest request = new BidRequest(Integer.valueOf(strings[0]),Integer.valueOf(strings[1]));

        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.deletePaymentMethod("https://auction-api-rest.herokuapp.com/users/" + strings[0] + "/payment_methods/" + strings[1]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
