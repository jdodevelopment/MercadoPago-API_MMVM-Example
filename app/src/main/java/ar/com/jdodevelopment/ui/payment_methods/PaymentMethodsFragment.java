package ar.com.jdodevelopment.ui.payment_methods;

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
import ar.com.jdodevelopment.databinding.PaymentMethodsFragmentBinding;
import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.util.view.OnItemClickListener;

public class PaymentMethodsFragment extends Fragment {


    private PaymentMethodsViewModel viewModel;
    private PaymentMethodsFragmentBinding binding;

    private PaymentMethodAdapter adapter;

    public static PaymentMethodsFragment newInstance() {
        return new PaymentMethodsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(PaymentMethodsViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.payment_methods_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        initRecyclerView();
        initObservers();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.consumePaymentMethods();
    }


    private void initObservers() {
        viewModel.getData().observe(getViewLifecycleOwner(), paymentMethods -> adapter.submitList(paymentMethods));
    }

    private void initRecyclerView() {
        OnItemClickListener<PaymentMethod> onItemClickListener = (view, obj) -> selectPaymentMethod(obj);
        adapter = new PaymentMethodAdapter(onItemClickListener);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    private void selectPaymentMethod(PaymentMethod object) {
        nextStep(object);
    }

    private void nextStep(PaymentMethod object) {
        Double amount = PaymentMethodsFragmentArgs.fromBundle(requireArguments()).getAmount();

        NavDirections navDirections = PaymentMethodsFragmentDirections.chooseBankAction(amount, object);
        NavHostFragment.findNavController(this).navigate(navDirections);
    }


}