package com.example.navigationdrawerpractica.Service;

import androidx.annotation.NonNull;

import com.example.navigationdrawerpractica.Entidades.PersonaPrueba;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.ResponseGetPaymentMethods;
import com.example.navigationdrawerpractica.Entidades.ResponseEntities.AccountResponse;
import com.example.navigationdrawerpractica.Entidades.SubastaClases.SubastaConArticulos;
import com.example.navigationdrawerpractica.Entidades.home.Auction;
import com.example.navigationdrawerpractica.Entidades.home.Home;
import com.example.navigationdrawerpractica.Entidades.requestEntities.BidRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.GeneratePasswordRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.RegisterRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.AccountRequest;
import com.example.navigationdrawerpractica.Entidades.requestEntities.CreditCardRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    Call<Void> registerUser(@Body RegisterRequest request);

    @GET("/home")
    Call<Home> getSubastas();

    @GET
    Call<Auction> getAuction(@Url String fullUrl);

    @GET
    Call<AccountResponse> getAAccountData(@Url String fullUrl);

    @GET
    Call<SubastaConArticulos> getAuctionWithItems(@Url String fullUrl);

    @GET
    Call<ResponseGetPaymentMethods> getPaymentMethods(@Url String fullUrl);

    @DELETE("/home")
    Call<PersonaPrueba> deletePaymentMethod(@Query(encoded = true, value = "id") @NonNull String idItemSelected);

    @PUT
    @Headers({"Content-Type: application/json"})
    Call<Void> putPaymentMethodAccount(@Url String fullUrl, @Body AccountRequest request);

    @PUT
    @Headers({"Content-Type: application/json"})
    Call<Void> putPaymentMethodCreditCard(@Url String fullUrl, @Body CreditCardRequest request);

    @PUT
    @Headers({"Content-Type: application/json"})
    Call<Void> bidAction(@Url String fullUrl, @Body BidRequest request);

}
