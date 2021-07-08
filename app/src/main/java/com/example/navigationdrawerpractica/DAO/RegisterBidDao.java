package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.BidRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.RegisterRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public class RegisterBidDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(String... strings) {
        BidRequest request = new BidRequest(Integer.valueOf(strings[0]),Integer.valueOf(strings[1]));

        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.bidAction("https://auction-api-rest.herokuapp.com/users/" + strings[2] + "/bids", request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
