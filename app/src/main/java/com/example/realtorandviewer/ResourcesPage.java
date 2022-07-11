package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ResourcesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page);

        ImageButton findRealtorBtn = findViewById(R.id.findRealtorBtn);
        ImageButton mortgageCalBtn = findViewById(R.id.mortgageCalculatorBtn);
        ImageButton findPropertiesBtn = findViewById(R.id.findPropertiesBtn);
        ImageButton profileBtn = findViewById(R.id.profileBtn);

        Button PreparingBtn = findViewById(R.id.Preparing);
        Button ListingBtn = findViewById(R.id.Listing);
        Button MarketingBtn = findViewById(R.id.Marketing);
        Button OfferBtn = findViewById(R.id.Offer);

        findRealtorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FindRealtor.class));
            }
        });
        findPropertiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FindProperties.class));
            }
        });
        mortgageCalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MortgageCalculator.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UserHome.class));
            }
        });

       PreparingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PreparingPage.class));
            }
        });

       ListingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListingPage.class));
            }
        });

        MarketingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MarketingPage.class));
            }
        });

        OfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), OfferPage.class));
            }
        });
    }
}