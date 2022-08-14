package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

public class MortgageCalculator extends AppCompatActivity {

    Button btnCalculate;
    EditText purchasePrice, downPayment, amortPeriod, interestRate;
    TextView monthlyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calculator);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.calculatorPage);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.realtorsPage:
                        startActivity(new Intent(getApplicationContext(), FindRealtor.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.propertiesPage:
                        startActivity(new Intent(getApplicationContext(), FindProperties.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.favouritesPage:
                        startActivity(new Intent(getApplicationContext(), Favourites.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.calculatorPage:
                        startActivity(new Intent(getApplicationContext(), MortgageCalculator.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.profilePage:
                        if (Login.uType == 1) {
                            startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        } else {
                            startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        return true;
                }
                return false;
            }
        });

        purchasePrice = findViewById(R.id.editTextPurchasePrice);
        downPayment = findViewById(R.id.editTextDownPayment);
        amortPeriod = findViewById(R.id.editTextAmortizationPeriod);
        interestRate = findViewById(R.id.editTextInterestRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        monthlyPayment = findViewById(R.id.monthlyPayment);

        btnCalculate.setOnClickListener(v -> {
            if (TextUtils.isEmpty(purchasePrice.getText().toString())) {
                purchasePrice.setError("Purchase Price is required");
                purchasePrice.requestFocus();
            } else if (TextUtils.isEmpty(downPayment.getText().toString())) {
                downPayment.setError("Down Payment is required");
                downPayment.requestFocus();
            } else if (TextUtils.isEmpty(amortPeriod.getText().toString())) {
                amortPeriod.setError("Please enter in amortization");
                amortPeriod.requestFocus();

            } else if (TextUtils.isEmpty(interestRate.getText().toString())) {
                interestRate.setError("Interest rate is required");
                interestRate.requestFocus();
            } else {
                //Store the user input values
                double userPurchasePrice = Double.parseDouble(purchasePrice.getText().toString());
                double userDownPayment = Double.parseDouble(downPayment.getText().toString());
                double userAmortPeriod = Double.parseDouble(amortPeriod.getText().toString());
                double userInterestRate = Double.parseDouble(interestRate.getText().toString());

                // variable to calculate and store the new price depending on the down payment
                double priceAfterDownPayment = userPurchasePrice - userDownPayment;

                //variable to calculate and store the number of monthly payments
                userAmortPeriod = userAmortPeriod * 12;

                //variable to calculate and store the monthly interest rate
                userInterestRate = (userInterestRate / 100) / 12;

                //formula to calculate the monthly mortgage payment
                float payment = (float) ((priceAfterDownPayment * userInterestRate) / (1 - Math.pow(1 + userInterestRate, -userAmortPeriod)));

                monthlyPayment.setText("$" + payment);
            }
        });
    }
}