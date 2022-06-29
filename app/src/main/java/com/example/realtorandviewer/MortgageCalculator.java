package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MortgageCalculator extends AppCompatActivity {

    Button btnCalculate;
    EditText purchasePrice, downPayment, amortPeriod, interestRate;
    TextView monthlyPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calculator);

        purchasePrice = findViewById(R.id.editTextPurchasePrice);
        downPayment = findViewById(R.id.editTextDownPayment);
        amortPeriod = findViewById(R.id.editTextAmortPeriod);
        interestRate = findViewById(R.id.editTextInterestRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        monthlyPayment = findViewById(R.id.monthlyPayment);

        btnCalculate.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(purchasePrice.getText().toString()) || TextUtils.isEmpty(downPayment.getText().toString()) || TextUtils.isEmpty(amortPeriod.getText().toString()) || TextUtils.isEmpty(interestRate.getText().toString())) {
                    Snackbar.make(v, "Please enter all the fields", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
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

                    monthlyPayment.setText("$" + String.valueOf(payment));


                }
            }
        });
    }
}