package ar.com.jdodevelopment.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatingUtil {


    public static String formatCurrency(double value){
        NumberFormat decimalFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        return decimalFormat.format(value);
    }

}
