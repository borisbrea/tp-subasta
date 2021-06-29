package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.home.Auction;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class ArticleDetailDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(String... strings) {
        Response<Auction> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getAuction("https://auction-api-rest.herokuapp.com/auctions/" + strings[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
