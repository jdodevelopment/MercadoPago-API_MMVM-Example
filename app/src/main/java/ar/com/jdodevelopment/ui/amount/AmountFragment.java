package ar.com.jdodevelopment.ui.amount;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.databinding.AmountFragmentBinding;

public class AmountFragment extends Fragment {

    private AmountViewModel viewModel;
    private AmountFragmentBinding binding;

    public static AmountFragment newInstance() {
        return new AmountFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,  @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(AmountViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.amount_fragment, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.button.setOnClickListener(view -> submitAmount());
        getArgumentsFromBundle();
        return binding.getRoot();
    }

    private void getArgumentsFromBundle() {
        boolean reset = AmountFragmentArgs.fromBundle(requireArguments()).getReset();
        if(reset){
            viewModel.getAmount().setValue(0D);
        }
    }


    private void submitAmount(){
        hideKeyboard();
        nextStep();
    }

    private void nextStep() {
        Double amount = viewModel.getAmount().getValue();
        NavDirections navDirections = AmountFragmentDirections.choosePaymentMethodAction(amount);
        NavHostFragment.findNavController(this).navigate(navDirections);
    }

    private void hideKeyboard() {
        final InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
    }


}