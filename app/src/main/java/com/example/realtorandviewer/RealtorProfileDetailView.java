package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class RealtorProfileDetailView extends AppCompatActivity {

    CircleImageView img;
    ImageButton backBtn;
    TextView firstNameText, lastNameText, companyText, realtorEmailText, realtorPhoneNumText;
    private DatabaseReference reference;
    RecyclerView recviewListing, recviewPast;
    myAdapterMyListingsProfileDetail myAdapterMyListingsProfileDetail;
    myAdapterMyPastSalesProfileDetail myAdapterMyPastSalesProfileDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_profile_detail_view);

        firstNameText = findViewById(R.id.fname_realtor_detail_view);
        lastNameText = findViewById(R.id.lname_realtor_detail_view);
        companyText = findViewById(R.id.company_realtor_detail_view);
        realtorEmailText = findViewById(R.id.email_realtor_detail_view);
        realtorPhoneNumText = findViewById(R.id.phone_realtor_detail_view);
        img = findViewById(R.id.realtor_profile_image);

        reference = FirebaseDatabase.getInstance().getReference("RealtorUsers");

        recviewListing = findViewById(R.id.rec_view_listing_profile);
        recviewListing.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Properties> options1 =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("MyProperties").child(Login.REALTOR_POSITION), Properties.class)
                        .build();

        myAdapterMyListingsProfileDetail = new myAdapterMyListingsProfileDetail(options1);
        recviewListing.setAdapter(myAdapterMyListingsProfileDetail);

        recviewPast = findViewById(R.id.rec_view_past_sale_profile);
        recviewPast.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Properties> options2 =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.REALTOR_POSITION), Properties.class)
                        .build();

        myAdapterMyPastSalesProfileDetail = new myAdapterMyPastSalesProfileDetail(options2);
        recviewPast.setAdapter(myAdapterMyPastSalesProfileDetail);

        reference.child(Login.REALTOR_POSITION).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String company = userProfile.company;
                    String email = userProfile.email;
                    String phone = userProfile.phNumber;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    companyText.setText(company);
                    realtorEmailText.setText(email);
                    realtorPhoneNumText.setText(phone);
                    Glide.with(img.getContext()).load(userProfile.getPimage()).into(img);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RealtorProfileDetailView.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });


        backBtn = findViewById(R.id.btn_back_to_find_realtors);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));


    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapterMyListingsProfileDetail.startListening();
        myAdapterMyPastSalesProfileDetail.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapterMyListingsProfileDetail.stopListening();
        myAdapterMyPastSalesProfileDetail.stopListening();
    }
}