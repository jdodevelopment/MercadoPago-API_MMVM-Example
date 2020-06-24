package ar.com.jdodevelopment.util.bindingadapter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;


import ar.com.jdodevelopment.util.FormatingUtil;

public class TextViewAdapters {


    @BindingAdapter({"currency"})
    public static void setCurrencyAndAmount(TextView textView, Double amount) {
        if(amount == null)
            amount = 0D;
        String formattedValue = FormatingUtil.formatCurrency(amount);
        textView.setText(formattedValue);
    }


    @InverseBindingAdapter(attribute = "android:text")
    public static Double getDouble(TextView view) {
        String num = view.getText().toString();
        if(num.isEmpty()) return 0.0D;
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            return 0.0D;
        }
    }


}
