package com.example.navigationdrawerpractica.Service;

import androidx.annotation.NonNull;

import com.example.navigationdrawerpractica.Entidades.Articulo;
import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.Subasta;
import com.example.navigationdrawerpractica.Entidades.home.Auction;
import com.example.navigationdrawerpractica.Entidades.home.Home;
import com.example.navigationdrawerpractica.Entidades.requestEntities.GeneratePasswordRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.RegisterRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitApiService {

    @GET("users/validate")
    Call<PersonaPrueba> validate(@Query(encoded = true, value = "email") @NonNull String email);
    @GET("users/validate")
    Call<List<PersonaPrueba>> validates(@Query(encoded = true, value = "email") @NonNull String email);

    @GET("users/login")
    Call<PersonaPrueba> login(@Query(encoded = true, value = "email") @NonNull String email, @Query(encoded = true, value = "password")  @NonNull String password);

    @POST("/users/code")
    @Headers({"Content-Type: application/json"})
    Call<Void> generatePassword(@Body GeneratePasswordRequest request);

    @POST("/users")
    @Headers({"Content-Type: application/json"})
    Call<Void> generatePassword(@Body RegisterRequest request);

    @GET("/home")
    Call<Home> getSubastas();

    @GET
    Call<Auction> getAuction(@Url String fullUrl);

    @DELETE("/home")
    Call<PersonaPrueba> deletePaymentMethod(@Query(encoded = true, value = "id") @NonNull String idItemSelected);
}
