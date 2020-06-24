package ar.com.jdodevelopment.ui.success;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.data.model.PayerCost;
import ar.com.jdodevelopment.data.model.PaymentMethod;

public class SuccessViewModel extends ViewModel {


    private final MutableLiveData<Double> amount;
    private final MutableLiveData<PaymentMethod> paymentMethod;
    private final MutableLiveData<CardIssuer> cardIssuer;
    private final MutableLiveData<PayerCost> payerCost;

    public SuccessViewModel() {
        this.amount = new MutableLiveData<>();
        this.paymentMethod = new MutableLiveData<>();
        this.cardIssuer = new MutableLiveData<>();
        this.payerCost = new MutableLiveData<>();
    }


    public MutableLiveData<Double> getAmount() {
        return amount;
    }


    public MutableLiveData<PaymentMethod> getPaymentMethod() {
        return paymentMethod;
    }

    public MutableLiveData<CardIssuer> getCardIssuer() {
        return cardIssuer;
    }

    public MutableLiveData<PayerCost> getPayerCost() {
        return payerCost;
    }
}