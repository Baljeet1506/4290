package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtorAddListings extends AppCompatActivity implements View.OnClickListener {

    EditText unitNumber, houseNumber, street, city, province, postal, price, beds, landSize, baths, floorSize, age, type, title;
    Button addListingBtn;
    ImageButton backBtn;
    DatabaseReference ref;
    Properties listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_listings);

        backBtn = findViewById(R.id.btnBack);

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

        addListingBtn = findViewById(R.id.btnAddListing);
        addListingBtn.setOnClickListener(this);

        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorListings.class)));

        listing = new Properties();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddListing:
                addListing();
                break;
        }
    }

    private void addListing() {
        String unitNumber_ = unitNumber.getText().toString();
        String houseNumber_ = houseNumber.getText().toString();
        String street_ = street.getText().toString();
        String city_ = city.getText().toString();
        String province_ = province.getText().toString();
        String postal_ = postal.getText().toString();
        String price_ = price.getText().toString();
        String beds_ = beds.getText().toString();
        String landSize_ = landSize.getText().toString();
        String baths_ = baths.getText().toString();
        String floorSize_ = floorSize.getText().toString();
        String age_ = age.getText().toString();
        String type_ = type.getText().toString();
        String title_ = title.getText().toString();

        if (houseNumber_.isEmpty()) {
            houseNumber.setError("House number is required");
            houseNumber.requestFocus();
            return;
        }
        if (street_.isEmpty()) {
            street.setError("Street is required");
            street.requestFocus();
            return;
        }
        if (city_.isEmpty()) {
            city.setError("City is required");
            city.requestFocus();
            return;
        }
        if (province_.isEmpty()) {
            province.setError("Province is required");
            province.requestFocus();
            return;
        }
        if (postal_.isEmpty()) {
            postal.setError("Postal code is required");
            postal.requestFocus();
            return;
        }
        if (price_.isEmpty()) {
            price.setError("Price is required");
            price.requestFocus();
            return;
        }
        if (beds_.isEmpty()) {
            beds.setError("Number of Bedrooms is required");
            beds.requestFocus();
            return;
        }
        if (baths_.isEmpty()) {
            baths.setError("Number of Bathrooms is required");
            baths.requestFocus();
            return;
        }
        if (floorSize_.isEmpty()) {
            floorSize.setError("Floor size is required");
            floorSize.requestFocus();
            return;
        }
        if (age_.isEmpty()) {
            age.setError("Age is required");
            age.requestFocus();
            return;
        }
        if (type_.isEmpty()) {
            type.setError("Type of property is required");
            type.requestFocus();
            return;
        }
        if (title_.isEmpty()) {
            title.setError("Title is required");
            title.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);
        ref = FirebaseDatabase.getInstance().getReference().child("Properties");

        listing.setUnitNumber(unitNumber_);
        listing.setHouseNumber(houseNumber_);
        listing.setStreet(street_);
        listing.setCity(city_);
        listing.setProvince(province_);
        listing.setPostal(postal_);
        listing.setPrice(price_);
        listing.setBeds(beds_);
        listing.setLandSize(landSize_);
        listing.setBaths(baths_);
        listing.setFloorSize(floorSize_);
        listing.setAge(age_);
        listing.setType(type_);
        listing.setTitle(title_);

        ref.push().setValue(listing);
        Toast.makeText(RealtorAddListings.this, "Listing is added successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), RealtorListings.class));
    }
}