package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class FindRealtor extends AppCompatActivity {
    ImageButton findPropertiesBtn, mortgageCalculatorBtn, profileBtn;
    RecyclerView recview;
    myAdapterFindRealtors adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_realtor);

        recview = (RecyclerView) findViewById(R.id.recViewFindRealtors);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RealtorUsers"), User.class)
                        .build();

        adapter = new myAdapterFindRealtors(options);
        recview.setAdapter(adapter);


        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);
        profileBtn = findViewById(R.id.btnProfile);

        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
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