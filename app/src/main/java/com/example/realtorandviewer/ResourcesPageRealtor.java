package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ResourcesPageRealtor extends AppCompatActivity {
    Button preparingBtn, listingBtn, marketingBtn, offerBtn;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalculatorBtn, profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page_realtor);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        profileBtn = findViewById(R.id.btnProfile);

        preparingBtn = findViewById(R.id.Preparing);
        listingBtn = findViewById(R.id.Listing);
        marketingBtn = findViewById(R.id.Marketing);
        offerBtn = findViewById(R.id.Offer);

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));

        preparingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorPrepareToSell.class)));
        listingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorListing.class)));
        marketingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorMarketing.class)));
        offerBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorOffer.class)));
    }
}