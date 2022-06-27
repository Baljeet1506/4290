package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyListingsPage extends AppCompatActivity {
  //  FloatingActionButton listingsBtn = findViewById(R.id.listingsBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings_page);

        FloatingActionButton listingsBtn = findViewById(R.id.listingsBtn);

        listingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), addListings.class));
            }
        });

    }
}