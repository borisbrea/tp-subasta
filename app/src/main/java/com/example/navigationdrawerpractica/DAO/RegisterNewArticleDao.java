package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.ArticleRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.RegisterRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class RegisterNewArticleDao extends AsyncTask<ArticleRequest, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(ArticleRequest... articleRequests) {
       // RegisterRequest request = new RegisterRequest(0,strings[2],strings[1] + " " + strings[5],strings[1], strings[5], strings[0], strings[3], strings[4]);

        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.newArticle("https://auction-api-rest.herokuapp.com/users/" + articleRequests[0].getUserId() + "/articles", articleRequests[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
