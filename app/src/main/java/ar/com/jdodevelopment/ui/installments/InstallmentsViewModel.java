package ar.com.jdodevelopment.ui.installments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ar.com.jdodevelopment.data.model.Installments;
import ar.com.jdodevelopment.data.repository.InstallmentsRepository;

public class InstallmentsViewModel extends ViewModel {


    private InstallmentsRepository repository;

    private LiveData<Boolean> loading;
    private LiveData<String> errorMessage;
    private LiveData<List<Installments>> data;


    public InstallmentsViewModel(){
        repository = new InstallmentsRepository();
        this.data = repository.getData();
        this.errorMessage = repository.getErrorMessage();
        this.loading = repository.getLoading();
    }


    public void consumeInstallments(String installmentsId, String issuerId, Double amount){
        repository.consumeInstallments(installmentsId, issuerId, amount);
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<List<Installments>> getData() {
        return data;
    }
}