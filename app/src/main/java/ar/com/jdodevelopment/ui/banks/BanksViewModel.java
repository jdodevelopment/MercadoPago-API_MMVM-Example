package ar.com.jdodevelopment.ui.banks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.data.repository.CardIssuersRepository;

public class BanksViewModel extends ViewModel {


    private CardIssuersRepository repository;

    private LiveData<Boolean> loading;
    private LiveData<String> errorMessage;
    private LiveData<List<CardIssuer>> data;


    public BanksViewModel(){
        repository = new CardIssuersRepository();
        this.data = repository.getData();
        this.errorMessage = repository.getErrorMessage();
        this.loading = repository.getLoading();
    }


    public void consumePaymentMethods(String paymentMethodId){
        repository.consumeCardIssuers(paymentMethodId);
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<CardIssuer>> getData() {
        return data;
    }
}