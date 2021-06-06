package com.example.navigationdrawerpractica.Service;

import androidx.annotation.NonNull;

import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApiService {

    @GET("users/validate")
    Call<PersonaPrueba> validate();
    @GET("users/validate")
    Call<List<PersonaPrueba>> validates(@Query(encoded = true, value = "email") @NonNull String email);

}
