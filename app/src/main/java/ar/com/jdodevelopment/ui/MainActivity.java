package ar.com.jdodevelopment.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


import ar.com.jdodevelopment.R;
import ar.com.jdodevelopment.databinding.ActivityMainBinding;
import ar.com.jdodevelopment.ui.success.SuccessFragmentDirections;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }


    @Override
    public void onBackPressed() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        if (navController.getCurrentDestination() != null && navController.getCurrentDestination().getId() == R.id.successFragment) {
            NavDirections navDirections = SuccessFragmentDirections.backToStartAction();
            navController.navigate(navDirections);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        if (navController.getCurrentDestination()!= null && navController.getCurrentDestination().getId() == R.id.successFragment) {
            NavDirections navDirections = SuccessFragmentDirections.backToStartAction();
            navController.navigate(navDirections);
            return false;
        }else{
            return navController.navigateUp();
        }
    }
}







