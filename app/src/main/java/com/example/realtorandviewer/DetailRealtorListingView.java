package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailRealtorListingView extends AppCompatActivity {

    ImageSlider mainslider;
    ImageButton backBtn;
    TextView price_Text, beds_Text, baths_Text, uNum_Text, houseNum_Text, street_Text, city_Text,
            province_Text, postal_Text, land_Text, floor_Text, age_Text, title_Text, type_Text,
            description_Text, fullNameText, companyText;
    String dollar = "$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_realtor_listing_view);

        backBtn = findViewById(R.id.btn_back_to_my_listing);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorListings.class)));

        mainslider = (ImageSlider) findViewById(R.id.image_slider_realtor);
        final List<SlideModel> remoteimages = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("MyProperties").child(Login.uID_).child(Login.MY_LISTING_POSITION.toString()).child("Images")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));

                        mainslider.setImageList(remoteimages, ScaleTypes.FIT);

                        mainslider.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                Toast.makeText(getApplicationContext(), remoteimages.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("MyProperties").child(Login.uID_);

        price_Text = findViewById(R.id.price_Text);
        beds_Text = findViewById(R.id.beds_Text);
        baths_Text = findViewById(R.id.baths_Text);
        uNum_Text = findViewById(R.id.uNum_Text);
        houseNum_Text = findViewById(R.id.houseNum_Text);
        street_Text = findViewById(R.id.street_Text);
        city_Text = findViewById(R.id.city_Text);
        province_Text = findViewById(R.id.province_Text);
        postal_Text = findViewById(R.id.postal_Text);
        land_Text = findViewById(R.id.land_Text);
        floor_Text = findViewById(R.id.floor_Text);
        age_Text = findViewById(R.id.age_Text);
        age_Text = findViewById(R.id.age_Text);
        title_Text = findViewById(R.id.title_Text);
        type_Text = findViewById(R.id.type_Text);
        description_Text = findViewById(R.id.description_Text);

        reference.child(Login.MY_LISTING_POSITION).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Properties prop = snapshot.getValue(Properties.class);

                if (prop != null) {
                    String price_Text_ = prop.price;
                    String beds_Text_ = prop.beds;
                    String baths_Text_ = prop.baths;
                    String uNum_Text_ = prop.unitNumber;
                    String houseNum_Text_ = prop.houseNumber;
                    String street_Text_ = prop.street;
                    String city_Text_ = prop.city;
                    String province_Text_ = prop.province;
                    String postal_Text_ = prop.postal;
                    String land_Text_ = prop.landSize;
                    String floor_Text_ = prop.floorSize;
                    String age_Text_ = prop.age;
                    String title_Text_ = prop.title;
                    String type_Text_ = prop.type;
                    String description_Text_ = prop.description;

                    price_Text.setText(dollar + price_Text_);
                    beds_Text.setText(beds_Text_);
                    baths_Text.setText(baths_Text_);
                    uNum_Text.setText(uNum_Text_);
                    houseNum_Text.setText(houseNum_Text_);
                    street_Text.setText(street_Text_);
                    city_Text.setText(city_Text_);
                    province_Text.setText(province_Text_);
                    postal_Text.setText(postal_Text_);
                    land_Text.setText(land_Text_ + Login.SQFT_);
                    floor_Text.setText(floor_Text_ + Login.SQFT_);
                    age_Text.setText(age_Text_);
                    title_Text.setText(Login.TITLE_ + title_Text_);
                    type_Text.setText(Login.TYPE_ + type_Text_);
                    description_Text.setText(description_Text_);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailRealtorListingView.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

        fullNameText = findViewById(R.id.realtor_Text);
        companyText = findViewById(R.id.realtor_company_Text);

        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("RealtorUsers");

        reference2.child(Login.uID_).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullname = userProfile.firstName + " " + userProfile.lastName;
                    String company = userProfile.company;

                    fullNameText.setText(fullname);
                    companyText.setText(company);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DetailRealtorListingView.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

    }
}