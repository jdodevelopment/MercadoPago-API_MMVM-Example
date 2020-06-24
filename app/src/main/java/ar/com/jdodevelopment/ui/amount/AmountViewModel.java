package ar.com.jdodevelopment.ui.amount;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ar.com.jdodevelopment.util.ParsingUtil;

public class AmountViewModel extends ViewModel {


    private final MutableLiveData<Double> amount;


    public AmountViewModel() {
        this.amount = new MutableLiveData<>();
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Double value = ParsingUtil.parseDouble(s.toString());
        amount.setValue(value);
    }

    public MutableLiveData<Double> getAmount() {
        return amount;
    }
}