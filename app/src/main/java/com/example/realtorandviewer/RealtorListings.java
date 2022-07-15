package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class RealtorListings extends AppCompatActivity {
    //  FloatingActionButton listingsBtn = findViewById(R.id.listingsBtn);
    ImageButton backBtn, findRealtorBtn, mortgageCalBtn, findPropertiesBtn, profileBtn;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_my_listings);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_listings);
        new FirebaseDatabaseHelper().readListings(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Listings> listingsList, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, RealtorListings.this, listingsList, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        backBtn = findViewById(R.id.btnBack);
        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        profileBtn = findViewById(R.id.btnProfile);
        FloatingActionButton listingsBtn = findViewById(R.id.listingsBtn);

        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageRealtor.class)));

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));

        listingsBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorAddListings.class)));
    }
}