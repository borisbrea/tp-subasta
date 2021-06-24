package com.example.navigationdrawerpractica.DAO;

import com.example.navigationdrawerpractica.Cliente.RetrofitClient;
import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.MetodoPago;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.requestEntities.GeneratePasswordRequest;
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

    public int login(String email, String clave){

        apiService = RetrofitClient.getApiService();
        apiService.login(email, clave).enqueue(new Callback<PersonaPrueba>() {
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

    public int generatePassword(String code, String password, String email){

        GeneratePasswordRequest request = new GeneratePasswordRequest(email, password,code );

        apiService = RetrofitClient.getApiService();
        apiService.generatePassword(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                responseValidate = response.code();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                responseValidate = 440;
            }
        });

        return responseValidate;
    }

    public void getSubastas(List<Subasta> subastas){

        subastas.add(new Subasta(1, "8/09/1990", "Oro"));
        subastas.add(new Subasta(2, "8/09/1990", "Plata"));

        /*apiService = RetrofitClient.getApiService();
        apiService.getSubastas(code, password).enqueue(new Callback<Subasta>() {
            @Override
            public void onResponse(Call<List<Subasta>> call, Response<List<Subasta>> response) {
                responseValidate = response.code();
                subastas.add(response)
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });
       */

    }

    public void getMetodosPago(List<MetodoPago> metodosPago){

        metodosPago.add(new MetodoPago(1, "credit_card", "Visa", "Tarjeta Visa", "XXXX XXXX XXXX 1234"));
        metodosPago.add(new MetodoPago(2, "bank_account", "Santander", "Cuenta Corriente Santander", "11224433551234"));

        /*apiService = RetrofitClient.getApiService();
        apiService.getSubastas(code, password).enqueue(new Callback<Subasta>() {
            @Override
            public void onResponse(Call<List<Subasta>> call, Response<List<Subasta>> response) {
                responseValidate = response.code();
                subastas.add(response)
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });
       */

    }

    public void getArticulos(List<Articulo> articulos){

        articulos.add(new Articulo("1", "15/06/1990", "Y", "Playstation 4", "Consola de videojuegos de la compañia Sony Interactive Etertaiment", "Hideo Kojima", "Boris Brea", "Pendiente de Venta"));
        articulos.add(new Articulo("2", "15/06/1990", "Y", "Casco VR", "Casco de realidad virtual para la consola PS4", "Yoshitaka Amano", "Boris Brea", "Pendiente de Subastar"));

        /*apiService = RetrofitClient.getApiService();
        apiService.getSubastas(code, password).enqueue(new Callback<Subasta>() {
            @Override
            public void onResponse(Call<List<Subasta>> call, Response<List<Subasta>> response) {
                responseValidate = response.code();
                subastas.add(response)
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });
       */

    }

    public void deletePaymentMethod(String idItemSelected) {
        apiService = RetrofitClient.getApiService();
        apiService.deletePaymentMethod(idItemSelected).enqueue(new Callback<PersonaPrueba>() {
            @Override
            public void onResponse(Call<PersonaPrueba> call, Response<PersonaPrueba> response) {
                responseValidate = response.code();
            }

            @Override
            public void onFailure(Call<PersonaPrueba> call, Throwable t) {
                responseValidate = 440;
            }
        });
    }
}
