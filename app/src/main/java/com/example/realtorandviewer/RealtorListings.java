package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RealtorListings extends AppCompatActivity {

    ImageButton backBtn, findRealtorBtn, mortgageCalBtn, findPropertiesBtn, profileBtn;
    RecyclerView recview;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_my_listings);

        recview = (RecyclerView) findViewById(R.id.recViewMyListings);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Properties> options =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MyProperties").child(Login.uID_), Properties.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);

        backBtn = findViewById(R.id.btnBack);
        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        profileBtn = findViewById(R.id.btnProfile);
        FloatingActionButton listingsBtn = findViewById(R.id.listingsBtn);

        backBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });

        listingsBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorAddListings.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}