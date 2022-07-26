package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class RealtorPastSales extends AppCompatActivity {
    ImageButton backBtn;
    RecyclerView recview;
    myAdapterMyPastSales adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_my_past_sales);

        recview = (RecyclerView) findViewById(R.id.recViewMyPastSales);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Properties> options =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.uID_), Properties.class)
                        .build();

        adapter = new myAdapterMyPastSales(options);
        recview.setAdapter(adapter);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });

        FloatingActionButton addPastSaleBtn = findViewById(R.id.addPastSaleBtn);
        addPastSaleBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorAddPastSale.class)));

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