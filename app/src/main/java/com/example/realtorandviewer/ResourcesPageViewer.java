package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ResourcesPageViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page_viewer);

        ImageButton findRealtorBtn = findViewById(R.id.findRealtorBtn);
        ImageButton mortgageCalBtn = findViewById(R.id.mortgageCalculatorBtn);
        ImageButton findPropertiesBtn = findViewById(R.id.findPropertiesBtn);
        ImageButton profileBtn = findViewById(R.id.profileBtn);

        Button PrepareToBuyBtn = findViewById(R.id.PrepareToBuy);
        Button PlanFinanceBtn = findViewById(R.id.PlanFinance);
        Button ViewPropertiesBtn = findViewById(R.id.ViewProperties);
        Button MakeAnOfferBtn = findViewById(R.id.MakeAnOffer);
        Button PurchasedBtn = findViewById(R.id.ClosePurchase);
        ImageButton BackBtn = findViewById(R.id.btnBack);

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
            }
        });

        PrepareToBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResourceViewerPrepareToBuy.class));
            }
        });

       PlanFinanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResourceViewerPlanFinance.class));
            }
        });

        ViewPropertiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResourceViewerViewProperties.class));
            }
        });

        MakeAnOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResourceViewerMakeAnOffer.class));
            }
        });

        PurchasedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResourceViewerPurchased.class));
            }
        });


    }
}