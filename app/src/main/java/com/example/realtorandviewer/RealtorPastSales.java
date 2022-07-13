package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RealtorPastSales extends AppCompatActivity {

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_my_past_sales);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageRealtor.class)));

    }
}