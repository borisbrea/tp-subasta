package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.requestEntities.RegisterRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.UserUpdateRequest;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;

import retrofit2.Response;

public class UserUpdateDao extends AsyncTask<UserUpdateRequest, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(UserUpdateRequest... userUpdateRequests) {
        Response<Void> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.updateUser("https://auction-api-rest.herokuapp.com/users/" + userUpdateRequests[0].getUserId(), userUpdateRequests[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
