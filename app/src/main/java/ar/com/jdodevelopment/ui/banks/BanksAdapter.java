package ar.com.jdodevelopment.ui.banks;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.databinding.ItemCardIssuerBinding;
import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.util.view.OnItemClickListener;


public class BanksAdapter extends ListAdapter<CardIssuer, BanksAdapter.CardIssuerViewHolder> {


    private static DiffUtil.ItemCallback<CardIssuer> callback = new DiffUtil.ItemCallback<CardIssuer>() {
        @Override
        public boolean areItemsTheSame(@NonNull CardIssuer oldItem, @NonNull CardIssuer newItem) {
            return newItem.getId().contentEquals(oldItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CardIssuer oldItem, @NonNull CardIssuer newItem) {
            return false;
        }
    };

    private final OnItemClickListener<CardIssuer> onItemClickListener;


    public BanksAdapter(OnItemClickListener<CardIssuer> onItemClickListener) {
        super(callback);
        this.onItemClickListener = onItemClickListener;
    }


    @NotNull
    @Override
    public CardIssuerViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ItemCardIssuerBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_card_issuer, viewGroup, false);
        return new CardIssuerViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NotNull CardIssuerViewHolder viewHolder, int position) {
        CardIssuer object = getItem(position);
        if(object != null){
            viewHolder.bindTo(object, this.onItemClickListener);
        }
    }


    static class CardIssuerViewHolder extends RecyclerView.ViewHolder {

        private ItemCardIssuerBinding binding;

        private CardIssuerViewHolder(ItemCardIssuerBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        void bindTo(CardIssuer object, OnItemClickListener<CardIssuer> onItemClickListener) {
            binding.setObject(object);
            binding.getRoot().setOnClickListener(view -> onItemClickListener.onItemClick(view, object));
        }

    }

}

