package ar.com.jdodevelopment.ui.payment_methods;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.data.repository.PaymentMethodsRepository;

public class PaymentMethodsViewModel extends ViewModel {


    private PaymentMethodsRepository repository;

    private LiveData<Boolean> loading;
    private LiveData<String> errorMessage;
    private LiveData<List<PaymentMethod>> data;


    public PaymentMethodsViewModel(){
        repository = new PaymentMethodsRepository();
        this.data = repository.getData();
        this.errorMessage = repository.getErrorMessage();
        this.loading = repository.getLoading();
    }


    public void consumePaymentMethods(){
        repository.consumePaymentMethods();
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<PaymentMethod>> getData() {
        return data;
    }
}