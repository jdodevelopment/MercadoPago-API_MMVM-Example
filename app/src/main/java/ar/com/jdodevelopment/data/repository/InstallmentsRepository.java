package ar.com.jdodevelopment.data.repository;

import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ar.com.jdodevelopment.data.model.Installments;
import ar.com.jdodevelopment.data.network.MercadoPagoRetrofit;
import ar.com.jdodevelopment.data.network.service.InstallmentsService;
import ar.com.jdodevelopment.util.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstallmentsRepository {


    private final InstallmentsService service;

    private MutableLiveData<Boolean> loading;
    private MutableLiveData<String> errorMessage;
    private MutableLiveData<List<Installments>> data;


    public InstallmentsRepository() {
        service = MercadoPagoRetrofit.create(InstallmentsService.class);
        loading = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        data = new MutableLiveData<>();
    }


    public void consumeInstallments(String paymentMethodId, String issuerId, Double amount){
        loading.setValue(true);
        data.setValue(new ArrayList<>());
        Call<List<Installments>> call = service.getInstallments(MercadoPagoRetrofit.PUBLIC_KEY, paymentMethodId, issuerId, amount );
        call.enqueue(new Callback<List<Installments>>() {
            @Override
            public void onResponse(@NotNull Call<List<Installments>> call, @NotNull Response<List<Installments>> response) {
                loading.setValue(false);
                handleResponse(response);
            }
            @Override
            public void onFailure(@NotNull Call<List<Installments>> call, @NotNull Throwable t) {
                loading.setValue(false);
                handleFailure(t);
            }
        });
    }


    private void handleResponse(Response<List<Installments>> response) {
        if (response.isSuccessful()) {
            data.setValue(response.body());
        } else {
            handleError(response);
        }
    }

    private void handleError(Response<List<Installments>> response) {
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

    public MutableLiveData<List<Installments>> getData() {
        return data;
    }
}
