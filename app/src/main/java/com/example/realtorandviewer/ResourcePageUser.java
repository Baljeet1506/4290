package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ResourcePageUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_page_user);

        Button PrepareToBuyBtn = findViewById(R.id.PrepareToBuy);
        Button PlanFinanceBtn = findViewById(R.id.PlanFinance);
        Button ViewPropertiesBtn = findViewById(R.id.ViewProperties);
        Button MakeAnOfferBtn = findViewById(R.id.MakeAnOffer);
        Button PurchasedBtn = findViewById(R.id.ClosePurchase);
        ImageButton BackBtn = findViewById(R.id.btnBack);

        PrepareToBuyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrepareToBuyPage.class));
            }
        });

       PlanFinanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PlanFinancePage.class));
            }
        });

        ViewPropertiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ViewPropertiesPage.class));
            }
        });

        MakeAnOfferBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MakeAnOfferPage.class));
            }
        });

        PurchasedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PurchasedPage.class));
            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UserHome.class));
            }
        });
    }
}