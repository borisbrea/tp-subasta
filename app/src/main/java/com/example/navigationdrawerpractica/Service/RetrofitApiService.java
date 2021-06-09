package com.example.navigationdrawerpractica.Service;

import androidx.annotation.NonNull;

import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitApiService {

    @GET("users/validate")
    Call<PersonaPrueba> validate(@Query(encoded = true, value = "email") @NonNull String email);
    @GET("users/validate")
    Call<List<PersonaPrueba>> validates(@Query(encoded = true, value = "email") @NonNull String email);

    @GET("users/validate")
    Call<PersonaPrueba> login(@Query(encoded = true, value = "usuario") @NonNull String usuario, @Query(encoded = true, value = "clave")  @NonNull String clave);

    @POST("/users/code")
    Call<PersonaPrueba> generatePassword(@Query(encoded = true, value = "code") @NonNull String code, @Query(encoded = true, value = "password")  @NonNull String password);


}
