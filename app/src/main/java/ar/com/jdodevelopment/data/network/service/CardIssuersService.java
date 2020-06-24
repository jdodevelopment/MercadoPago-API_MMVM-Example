package ar.com.jdodevelopment.data.network.service;

import java.util.List;

import ar.com.jdodevelopment.data.model.CardIssuer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CardIssuersService {



    @GET("payment_methods/card_issuers")
    Call<List<CardIssuer>> getCardIssuers(
            @Query("public_key") String publicKey,
            @Query("payment_method_id") String paymentMethodId);


}

