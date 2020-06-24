package ar.com.jdodevelopment.ui.installments;

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

import java.util.ArrayList;
import java.util.List;

import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.data.model.CardIssuer;
import ar.com.jdodevelopment.data.model.PayerCost;
import ar.com.jdodevelopment.data.model.PaymentMethod;
import ar.com.jdodevelopment.databinding.InstallmentsFragmentBinding;
import ar.com.jdodevelopment.ui.banks.BanksFragmentArgs;
import ar.com.jdodevelopment.util.view.OnItemClickListener;

public class InstallmentsFragment extends Fragment {


    private InstallmentsViewModel viewModel;
    private InstallmentsFragmentBinding binding;

    private PayerCostsAdapter adapter;

    public static InstallmentsFragment newInstance() {
        return new InstallmentsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(InstallmentsViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.installments_fragment, container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
        initRecyclerView();
        initObservers();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Double amount = InstallmentsFragmentArgs.fromBundle(requireArguments()).getAmount();
        CardIssuer cardIssuer = InstallmentsFragmentArgs.fromBundle(requireArguments()).getCardIssuer();
        PaymentMethod paymentMethod = InstallmentsFragmentArgs.fromBundle(requireArguments()).getPaymentMethod();
        viewModel.consumeInstallments(paymentMethod.getId(),cardIssuer.getId(), amount);
    }


    private void initObservers() {
        viewModel.getData().observe(getViewLifecycleOwner(), installments -> {
            if(!installments.isEmpty()){
                List<PayerCost> payerCosts = installments.get(0).getPayerCosts();
                adapter.submitList(payerCosts);
            }else{
                adapter.submitList(new ArrayList<>());
            }
        });
    }

    private void initRecyclerView() {
        OnItemClickListener<PayerCost> onItemClickListener = (view, obj) -> selectInstallments(obj);
        adapter = new PayerCostsAdapter(onItemClickListener);
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);
    }

    private void selectInstallments(PayerCost object) {
        nextStep(object);
    }

    private void nextStep(PayerCost object) {
        Double amount = BanksFragmentArgs.fromBundle(requireArguments()).getAmount();
        PaymentMethod paymentMethod = InstallmentsFragmentArgs.fromBundle(requireArguments()).getPaymentMethod();
        CardIssuer cardIssuer =  InstallmentsFragmentArgs.fromBundle(requireArguments()).getCardIssuer();

        NavDirections navDirections = InstallmentsFragmentDirections.showSuccess(amount, paymentMethod, cardIssuer, object);
        NavHostFragment.findNavController(this).navigate(navDirections);
    }


}