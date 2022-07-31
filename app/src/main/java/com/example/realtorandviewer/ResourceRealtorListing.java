package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ResourceRealtorListing extends AppCompatActivity {
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_realtor_listing);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageRealtor.class)));
    }
}