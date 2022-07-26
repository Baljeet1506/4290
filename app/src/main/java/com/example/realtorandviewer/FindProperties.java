package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FindProperties extends AppCompatActivity {
    ImageButton findRealtorBtn, mortgageCalculatorBtn, profileBtn;
    RecyclerView recview;
    myAdapterFindProperties adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_properties);

        recview = (RecyclerView) findViewById(R.id.recViewFindProperties);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Properties> options =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AllProperties"), Properties.class)
                        .build();

        adapter = new myAdapterFindProperties(options);
        recview.setAdapter(adapter);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        profileBtn = findViewById(R.id.btnProfile);

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        profileBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });


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