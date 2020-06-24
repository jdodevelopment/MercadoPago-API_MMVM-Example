package ar.com.jdodevelopment.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Installments {


    @SerializedName("id")
    private String id;


    @SerializedName("payer_costs")
    private List<PayerCost> payerCosts;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PayerCost> getPayerCosts() {
        return payerCosts;
    }

    public void setPayerCosts(List<PayerCost> payerCosts) {
        this.payerCosts = payerCosts;
    }
}
