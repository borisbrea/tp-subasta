package com.example.navigationdrawerpractica.DAO;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Service.RetrofitApiService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenericDao {

    //Conexión a la Api
    private RetrofitApiService apiService = RetrofitClient.getApiService();
    public static int responseValidate = 0;

    public static void genericDao(){
        String prueba = "";
    }

    public int validate(String email){

        apiService = RetrofitClient.getApiService();
        apiService.validate(email).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });

        return responseValidate;
    }

    public int login(String usuario, String clave){

        apiService = RetrofitClient.getApiService();
        apiService.login(usuario, clave).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });

        return responseValidate;
    }

    public int generatePassword(String code, String password){
        apiService = RetrofitClient.getApiService();
        apiService.generatePassword(code, password).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });

        return responseValidate;
    }

    public List<Subasta> getSubastas(){

        List<Subasta> subastas = new ArrayList<>();
        subastas.add(new Subasta(1, "8/09/1990", "Oro"));
        subastas.add(new Subasta(2, "8/09/1990", "Plata"));

        return subastas;
    }

}
