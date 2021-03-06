package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ArticleResponse;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ArticleDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(String... strings) {
        Response<List<ArticleResponse>> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getArticles("https://auction-api-rest.herokuapp.com/users/" + strings[0] + "/articles").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}