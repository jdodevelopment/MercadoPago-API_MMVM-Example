package ar.com.jdodevelopment.data.repository;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ar.com.jdodevelopment.data.network.MercadoPagoRetrofit;
import ar.com.jdodevelopment.data.network.service.PaymentMethodsService;
import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodsRepository {


    private final PaymentMethodsService service;

    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<List<PaymentMethod>> data;


    public PaymentMethodsRepository() {
        service = MercadoPagoRetrofit.create(PaymentMethodsService.class);
        loading = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        data = new MutableLiveData<>();
    }


    public void consumePaymentMethods(){
        loading.setValue(true);
        data.setValue(new ArrayList<>());
        Call<List<PaymentMethod>> call = service.getPaymentMethods(MercadoPagoRetrofit.PUBLIC_KEY);
        call.enqueue(new Callback<List<PaymentMethod>>() {
            @Override
            public void onResponse(@NotNull Call<List<PaymentMethod>> call, @NotNull Response<List<PaymentMethod>> response) {
                loading.setValue(false);
                handleResponse(response);
            }
            @Override
            public void onFailure(@NotNull Call<List<PaymentMethod>> call, @NotNull Throwable t) {
                loading.setValue(false);
                handleFailure(t);
            }
        });
    }


    private void handleResponse(Response<List<PaymentMethod>> response) {
        if (response.isSuccessful()) {
            data.setValue(response.body());
        } else {
            handleError(response);
        }
    }

    private void handleError(Response<List<PaymentMethod>> response) {
        String message = RetrofitUtil.getErrorMessage(response);
        errorMessage.setValue(message);
    }

    private void handleFailure(Throwable throwable) {
        throwable.printStackTrace();
        String message = throwable.getMessage();
        errorMessage.setValue(message);
    }


    /**
     * LiveData Getters
     */

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<PaymentMethod>> getData() {
        return data;
    }
}
