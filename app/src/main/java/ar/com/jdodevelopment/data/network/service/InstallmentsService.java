package ar.com.jdodevelopment.data.network.service;

import java.util.List;

import ar.com.jdodevelopment.data.model.Installments;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InstallmentsService {



    @GET("payment_methods/installments")
    Call<List<Installments>> getInstallments(
            @Query("public_key") String publicKey,
            @Query("payment_method_id") String paymentMethodId,
            @Query("issuer.id") String issuerId,
            @Query("amount") Double amount
    );


}

