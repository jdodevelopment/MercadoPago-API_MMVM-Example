package ar.com.jdodevelopment.ui.payment_methods;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.databinding.ItemPaymentMethodBinding;
import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.util.view.OnItemClickListener;


public class PaymentMethodAdapter extends ListAdapter<PaymentMethod, PaymentMethodAdapter.PaymentMethodViewHolder> {


    private static DiffUtil.ItemCallback<PaymentMethod> callback = new DiffUtil.ItemCallback<PaymentMethod>() {
        @Override
        public boolean areItemsTheSame(@NonNull PaymentMethod oldItem, @NonNull PaymentMethod newItem) {
            return newItem.getId().contentEquals(oldItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PaymentMethod oldItem, @NonNull PaymentMethod newItem) {
            return false;
        }
    };

    private final OnItemClickListener<PaymentMethod> onItemClickListener;


    public PaymentMethodAdapter(OnItemClickListener<PaymentMethod> onItemClickListener) {
        super(callback);
        this.onItemClickListener = onItemClickListener;
    }


    @NotNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemPaymentMethodBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_payment_method, viewGroup, false);
        return new PaymentMethodViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NotNull PaymentMethodViewHolder viewHolder, int position) {
        PaymentMethod object = getItem(position);
        if(object != null){
            viewHolder.bindTo(object, this.onItemClickListener);
        }
    }


    static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        private ItemPaymentMethodBinding binding;

        private PaymentMethodViewHolder(ItemPaymentMethodBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        void bindTo(PaymentMethod object, OnItemClickListener<PaymentMethod> onItemClickListener) {
            binding.setObject(object);
            binding.getRoot().setOnClickListener(view -> onItemClickListener.onItemClick(view, object));
        }

    }

}

