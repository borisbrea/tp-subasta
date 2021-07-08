package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.SubastaClases.SubastaConArticulos;
import com.example.navigationdrawerpractica.Entidades.home.Auction;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class AuctionWithItemsDao extends AsyncTask<Integer, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(Integer... ints) {
        Response<SubastaConArticulos> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getAuctionWithItems("https://auction-api-rest.herokuapp.com/auctions/" + ints[0] + "/catalog", ints[1]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}

