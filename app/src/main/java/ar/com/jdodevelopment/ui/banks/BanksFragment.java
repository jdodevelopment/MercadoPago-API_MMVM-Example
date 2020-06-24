package ar.com.jdodevelopment.ui.banks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.databinding.BanksFragmentBinding;
import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.util.view.OnItemClickListener;

public class BanksFragment extends Fragment {


    private BanksViewModel viewModel;
    private BanksFragmentBinding binding;

    private BanksAdapter adapter;

    public static BanksFragment newInstance() {
        return new BanksFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(BanksViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.banks_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        initRecyclerView();
        initObservers();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: Payment method deberia almacenarce en el viewmodel?
        PaymentMethod paymentMethod = BanksFragmentArgs.fromBundle(requireArguments()).getPaymentMethod();
        viewModel.consumePaymentMethods(paymentMethod.getId());
    }


    private void initObservers() {
        viewModel.getData().observe(getViewLifecycleOwner(), paymentMethods -> adapter.submitList(paymentMethods));
    }

    private void initRecyclerView() {
        OnItemClickListener<CardIssuer> onItemClickListener = (view, obj) -> selectPaymentMethod(obj);
        adapter = new BanksAdapter(onItemClickListener);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    private void selectPaymentMethod(CardIssuer object) {
        nextStep(object);
    }

    private void nextStep(CardIssuer object) {
        Double amount = BanksFragmentArgs.fromBundle(requireArguments()).getAmount();
        PaymentMethod paymentMethod = BanksFragmentArgs.fromBundle(requireArguments()).getPaymentMethod();

        NavDirections navDirections = BanksFragmentDirections.chooseInstallments(amount, paymentMethod, object);
        NavHostFragment.findNavController(this).navigate(navDirections);
    }


}