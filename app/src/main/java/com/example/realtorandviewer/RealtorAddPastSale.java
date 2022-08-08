package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RealtorAddPastSale extends AppCompatActivity implements View.OnClickListener {

    EditText unitNumber, houseNumber, street, city, province, postal, price, beds, landSize, baths, floorSize, age, description;
    Spinner type, title;
    Button addPastSaleBtn;
    ImageButton backBtn;
    DatabaseReference ref;
    Properties listing;
    Spinner spinnerType, spinnerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_add_past_sale);

        unitNumber = findViewById(R.id.editTextUnitNumberPast);
        houseNumber = findViewById(R.id.editTextHouseNumberPast);
        street = findViewById(R.id.editTextStreetPast);
        city = findViewById(R.id.editTextCityPast);
        province = findViewById(R.id.editTextProvincePast);
        postal = findViewById(R.id.editTextPostalPast);
        price = findViewById(R.id.editTextPricePast);
        beds = findViewById(R.id.editTextBedsPast);
        landSize = findViewById(R.id.editTextLandSizePast);
        baths = findViewById(R.id.editTextBathsPast);
        floorSize = findViewById(R.id.editTextFloorSizePast);
        age = findViewById(R.id.editTextAgePast);
        type = findViewById(R.id.spinnerTypePast);
        title = findViewById(R.id.spinnerTitlePast);
        description = findViewById(R.id.editTextDescriptionPast);

        addPastSaleBtn = findViewById(R.id.btnAddPastSale);
        addPastSaleBtn.setOnClickListener(this);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(this);
        listing = new Properties();

        String[] property_type_array = getResources().getStringArray(R.array.property_type_array);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.property_type_array, R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        type.setAdapter(typeAdapter);

        String[] property_title_array = getResources().getStringArray(R.array.property_title_array);
        ArrayAdapter<CharSequence> titleAdapter = ArrayAdapter.createFromResource(this, R.array.property_title_array, R.layout.simple_spinner_item);
        titleAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        title.setAdapter(titleAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddPastSale:
                addPastSale();
                break;
            case R.id.btnBack:
                startActivity(new Intent(getApplicationContext(), RealtorPastSales.class));
                break;
        }
    }

    private void addPastSale() {

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
        String type_ = type.getSelectedItem().toString();
        String title_ = title.getSelectedItem().toString();
        String description_ = description.getText().toString();
        String listingImage_ = "";

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

        if (landSize_.isEmpty()) {
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
            TextView errorText = (TextView) spinnerType.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Type of property is required");
            type.requestFocus();
            return;
        }
        if (title_.isEmpty()) {
            TextView errorText = (TextView) spinnerTitle.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);
            errorText.setText("Title of property is required");
            title.requestFocus();
            return;
        }

        if (description_.isEmpty()) {
            description.setError("Please enter a description");
            description.requestFocus();
            return;
        }

        ref = FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.uID_);

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
        listing.setdescription(description_);
        listing.setListingImage(listingImage_);
        //ref.push().setValue(listing);

        DatabaseReference blankRecordReference = ref;
        DatabaseReference db_ref = blankRecordReference.push();
        Login.str_NEW_Records_Key_PAST_SALE = db_ref.getKey();
        db_ref.setValue(listing);

        Toast.makeText(RealtorAddPastSale.this, "Listing is added successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), UploadImagesPastSale.class));

    }
}