package ar.com.jdodevelopment.ui.success;

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

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.data.model.PayerCost;
import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.databinding.SuccessFragmentBinding;

public class SuccessFragment extends Fragment {


    private SuccessViewModel viewModel;
    private SuccessFragmentBinding binding;

//    private MainViewModel mainViewModel;

    public static SuccessFragment newInstance() {
        return new SuccessFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,  @Nullable Bundle savedInstanceState) {
//        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(SuccessViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.success_fragment, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        getParamsFromBudle();
        initListeners();
        return binding.getRoot();
    }

    private void initListeners() {
        binding.button.setOnClickListener(view -> {
            NavDirections navDirections = SuccessFragmentDirections.backToStartAction();
            NavHostFragment.findNavController(this).navigate(navDirections);
        });
    }

    private void getParamsFromBudle() {
        Double amount = SuccessFragmentArgs.fromBundle(requireArguments()).getAmount();
        PaymentMethod paymentMethod = SuccessFragmentArgs.fromBundle(requireArguments()).getPaymentMethod();
        CardIssuer cardIssuer = SuccessFragmentArgs.fromBundle(requireArguments()).getCardIssuer();
        PayerCost payerCost = SuccessFragmentArgs.fromBundle(requireArguments()).getPayerCost();
        viewModel.getAmount().setValue(amount);
        viewModel.getPaymentMethod().setValue(paymentMethod);
        viewModel.getCardIssuer().setValue(cardIssuer);
        viewModel.getPayerCost().setValue(payerCost);
    }




}