package com.example.navigationdrawerpractica.Service;

import androidx.annotation.NonNull;

import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.Subasta;

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

    @GET("users/login")
    Call<PersonaPrueba> login(@Query(encoded = true, value = "email") @NonNull String email, @Query(encoded = true, value = "password")  @NonNull String password);

    @POST("/users/code")
    Call<PersonaPrueba> generatePassword(@Query(encoded = true, value = "code") @NonNull String code, @Query(encoded = true, value = "password")  @NonNull String password);

    @GET("/home")
    Call<List<Subasta>> getSubastas();
}
