package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class FindRealtor extends AppCompatActivity {
    ImageButton findPropertiesBtn, mortgageCalculatorBtn, profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_realtor);

        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        profileBtn = findViewById(R.id.btnProfile);

        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));
    }


}