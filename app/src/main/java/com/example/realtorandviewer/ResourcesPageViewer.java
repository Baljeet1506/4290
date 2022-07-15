package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ResourcesPageViewer extends AppCompatActivity {
    CardView preparingToBuyBtn, financePlanningBtn, viewPropertiesBtn, makeAnOfferBtn, closingPurchaseBtn;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalculatorBtn, profileBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page_viewer);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        profileBtn = findViewById(R.id.btnProfile);

        preparingToBuyBtn = findViewById(R.id.btnPreparingToBuy);
        financePlanningBtn = findViewById(R.id.btnFinancePlanning);
        viewPropertiesBtn = findViewById(R.id.btnViewProperties);
        makeAnOfferBtn = findViewById(R.id.btnMakeAnOffer);
        closingPurchaseBtn = findViewById(R.id.btnClosingPurchase);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });

        preparingToBuyBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPrepareToBuy.class)));
        financePlanningBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPlanFinance.class)));
        viewPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerViewProperties.class)));
        makeAnOfferBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerMakeAnOffer.class)));
        closingPurchaseBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPurchased.class)));
    }
}