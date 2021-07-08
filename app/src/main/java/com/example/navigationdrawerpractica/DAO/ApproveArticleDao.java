package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ArticleResponse;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class ApproveArticleDao extends AsyncTask<String, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(String... strings) {
        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.approveArticle("https://auction-api-rest.herokuapp.com/users/" + strings[0] + "/articles/" + strings[1]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}