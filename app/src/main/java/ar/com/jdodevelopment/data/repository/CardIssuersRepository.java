package ar.com.jdodevelopment.data.repository;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ar.com.jdodevelopment.data.network.MercadoPagoRetrofit;
import ar.com.jdodevelopment.data.network.service.CardIssuersService;
import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardIssuersRepository {


    private final CardIssuersService service;

    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<List<CardIssuer>> data;


    public CardIssuersRepository() {
        service = MercadoPagoRetrofit.create(CardIssuersService.class);
        loading = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        data = new MutableLiveData<>();
    }


    public void consumeCardIssuers(String paymentMethodId){
        loading.setValue(true);
        data.setValue(new ArrayList<>());
        Call<List<CardIssuer>> call = service.getCardIssuers(MercadoPagoRetrofit.PUBLIC_KEY, paymentMethodId);
        call.enqueue(new Callback<List<CardIssuer>>() {
            @Override
            public void onResponse(@NotNull Call<List<CardIssuer>> call, @NotNull Response<List<CardIssuer>> response) {
                loading.setValue(false);
                handleResponse(response);
            }
            @Override
            public void onFailure(@NotNull Call<List<CardIssuer>> call, @NotNull Throwable t) {
                loading.setValue(false);
                handleFailure(t);
            }
        });
    }


    private void handleResponse(Response<List<CardIssuer>> response) {
        if (response.isSuccessful()) {
            data.setValue(response.body());
        } else {
            handleError(response);
        }
    }

    private void handleError(Response<List<CardIssuer>> response) {
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

    public MutableLiveData<List<CardIssuer>> getData() {
        return data;
    }
}
