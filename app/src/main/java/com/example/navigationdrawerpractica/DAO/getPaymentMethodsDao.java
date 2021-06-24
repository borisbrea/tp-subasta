package com.example.navigationdrawerpractica.DAO;

import android.os.AsyncTask;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ResponseGetPaymentMethods;
import com.example.navigationdrawerpractica.Entidades.SubastaClases.SubastaConArticulos;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class getPaymentMethodsDao extends AsyncTask<Integer, Void, Response> {

    private RetrofitApiService apiService = RetrofitClient.getApiService();

    @Override
    protected Response doInBackground(Integer... integers) {
        Response<ResponseGetPaymentMethods> response = null;

        apiService = RetrofitClient.getApiService();
        try {
            response = apiService.getPaymentMethods("https://auction-api-rest.herokuapp.com/users/" + integers[0] + "/payment_methods").execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
