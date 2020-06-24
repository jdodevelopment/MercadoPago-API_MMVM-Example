package ar.com.jdodevelopment.ui.installments;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.data.model.PayerCost;
import ar.com.jdodevelopment.databinding.ItemPayerCostsBinding;
import ar.com.jdodevelopment.util.view.OnItemClickListener;


public class PayerCostsAdapter extends ListAdapter<PayerCost, PayerCostsAdapter.PayerCostViewHolder> {


    private static DiffUtil.ItemCallback<PayerCost> callback = new DiffUtil.ItemCallback<PayerCost>() {
        @Override
        public boolean areItemsTheSame(@NonNull PayerCost oldItem, @NonNull PayerCost newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PayerCost oldItem, @NonNull PayerCost newItem) {
            return false;
        }
    };

    private final OnItemClickListener<PayerCost> onItemClickListener;


    public PayerCostsAdapter(OnItemClickListener<PayerCost> onItemClickListener) {
        super(callback);
        this.onItemClickListener = onItemClickListener;
    }


    @NotNull
    @Override
    public PayerCostViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemPayerCostsBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_payer_costs, viewGroup, false);
        return new PayerCostViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NotNull PayerCostViewHolder viewHolder, int position) {
        PayerCost object = getItem(position);
        if(object != null){
            viewHolder.bindTo(object, this.onItemClickListener);
        }
    }


    static class PayerCostViewHolder extends RecyclerView.ViewHolder {

        private ItemPayerCostsBinding binding;

        private PayerCostViewHolder(ItemPayerCostsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        void bindTo(PayerCost object, OnItemClickListener<PayerCost> onItemClickListener) {
            binding.setObject(object);
            binding.getRoot().setOnClickListener(view -> onItemClickListener.onItemClick(view, object));
        }

    }

}

