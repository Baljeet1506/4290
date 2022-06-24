package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MortgageCalculator extends AppCompatActivity {

    Button btnCalculate;
    EditText purchasePrice, downPayment, amortPeriod, interestRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage_calculator);

        purchasePrice = findViewById(R.id.editTextPurchasePrice);
        downPayment = findViewById(R.id.editTextDownPayment);
        amortPeriod = findViewById(R.id.editTextAmortPeriod);
        interestRate = findViewById(R.id.editTextInterestRate);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Store the user input values
                double userPurchasePrice = Double.parseDouble(purchasePrice.getText().toString());
                double userdownPayment = Double.parseDouble(downPayment.getText().toString());
                double useramortPeriod = Double.parseDouble(amortPeriod.getText().toString());
                double userinterestRate = Double.parseDouble(interestRate.getText().toString());



                    // variable to calculate and store the new price depending on the down payment
                    double priceAfterDownPayment = userPurchasePrice - userdownPayment;

                    //variable to calculate and store the number of monthly payments
                    useramortPeriod = useramortPeriod * 12;

                    //variable to calculate and store the monthly interest rate
                    userinterestRate = (userinterestRate/100)/12;

                    //formula to calculate the monthly mortgage payment
                    float payment = (float) ((priceAfterDownPayment * userinterestRate) / (1 - Math.pow(1 + userinterestRate, -useramortPeriod)));

                    Toast.makeText(MortgageCalculator.this, " " + payment, Toast.LENGTH_LONG).show();


            }
        });

    }
}