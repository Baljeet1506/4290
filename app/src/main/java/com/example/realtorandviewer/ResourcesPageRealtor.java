package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ResourcesPageRealtor extends AppCompatActivity {
    CardView preparingToSellBtn, listingBtn, marketingBtn, theOfferBtn;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalculatorBtn, profileBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page_realtor);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        profileBtn = findViewById(R.id.btnProfile);

        preparingToSellBtn = findViewById(R.id.btnPreparingToSell);
        listingBtn = findViewById(R.id.btnListing);
        marketingBtn = findViewById(R.id.btnMarketing);
        theOfferBtn = findViewById(R.id.btnTheOffer);

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

        preparingToSellBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorPrepareToSell.class)));
        listingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorListing.class)));
        marketingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorMarketing.class)));
        theOfferBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorOffer.class)));
    }
}