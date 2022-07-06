package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class addListings extends AppCompatActivity implements View.OnClickListener {

    EditText unitNumber, houseNumber, street, city, province, postal, price, beds, landSize, baths, floorSize, age, type, title;
    Button btnAddListing, btnCancelListing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listings);


        unitNumber = findViewById(R.id.editTextUnitNum);
        houseNumber = findViewById(R.id.editTextHouseNum);
        street = findViewById(R.id.editTextStreet);
        city = findViewById(R.id.editTextCity);
        province = findViewById(R.id.editTextProvince);
        postal = findViewById(R.id.editTextPostal);
        price = findViewById(R.id.editTextPrice);
        beds = findViewById(R.id.editTextBeds);
        landSize = findViewById(R.id.editTextLand);
        baths = findViewById(R.id.editTextBaths);
        floorSize = findViewById(R.id.editTextFloorSize);
        age = findViewById(R.id.editTextAge);
        type = findViewById(R.id.editTextType);
        title = findViewById(R.id.editTextTitle);

        btnAddListing = findViewById(R.id.btnAddListing);
        btnAddListing.setOnClickListener(this);
        btnCancelListing = findViewById(R.id.btnCancelListing);
        btnCancelListing.setOnClickListener(this);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btnAddListing:
                addListing();
                break;
            case R.id.btnCancelListing:
                startActivity(new Intent(getApplicationContext(), MyListingsPage.class));
                break;
        }
    }


    private void addListing() {


    }

}