package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ResourcesPageViewer extends AppCompatActivity {
    Button prepareToBuyBtn, planFinanceBtn, viewPropertiesBtn, makeAnOfferBtn, purchasedBtn;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalculatorBtn, profileBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page_viewer);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        profileBtn = findViewById(R.id.btnProfile);

        prepareToBuyBtn = findViewById(R.id.PrepareToBuy);
        planFinanceBtn = findViewById(R.id.PlanFinance);
        viewPropertiesBtn = findViewById(R.id.ViewProperties);
        makeAnOfferBtn = findViewById(R.id.MakeAnOffer);
        purchasedBtn = findViewById(R.id.ClosePurchase);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));

        prepareToBuyBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPrepareToBuy.class)));
        planFinanceBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPlanFinance.class)));
        viewPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerViewProperties.class)));
        makeAnOfferBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerMakeAnOffer.class)));
        purchasedBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPurchased.class)));

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));
    }
}