package ar.com.jdodevelopment.data.network.service;

import java.util.List;

import ar.com.jdodevelopment.data.model.PaymentMethod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PaymentMethodsService {



    @GET("payment_methods")
    Call<List<PaymentMethod>> getPaymentMethods(@Query("public_key") String public_key);


}

