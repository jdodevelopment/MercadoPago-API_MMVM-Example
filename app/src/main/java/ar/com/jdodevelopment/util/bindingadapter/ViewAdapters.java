package ar.com.jdodevelopment.util.bindingadapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class ViewAdapters {


    @BindingAdapter("visible")
    public static void setVisibility(View view, Boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }

}
