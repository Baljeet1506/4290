package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePageRealtor extends AppCompatActivity {

    private Button logoutBtn;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalculatorBtn;
    TextView firstNameText, lastNameText, realtorEmailText, realtorPhoneNumText;
    CardView favouritesBtn, myListingsBtn, pastSalesBtn, resourcesBtn;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_realtor);

        logoutBtn = findViewById(R.id.btnLogout);

        favouritesBtn = findViewById(R.id.btnFavourites);
        myListingsBtn = findViewById(R.id.btnMyListings);
        pastSalesBtn = findViewById(R.id.btnPastSales);
        resourcesBtn = findViewById(R.id.btnResources);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);

        firstNameText = findViewById(R.id.textFirstName);
        lastNameText = findViewById(R.id.textLastName);
        realtorEmailText = findViewById(R.id.textRealtorEmail);
        realtorPhoneNumText = findViewById(R.id.textRealtorPhone);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String email = userProfile.email;
                    String phone = userProfile.phNumber;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    realtorEmailText.setText(email);
                    realtorPhoneNumText.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageRealtor.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

        favouritesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Favourites.class)));
        myListingsBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorListings.class)));
        pastSalesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorPastSales.class)));
        resourcesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourcesPageRealtor.class)));

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(HomePageRealtor.this, Login.class);
                startActivity(intentL);
                finish();
                Toast.makeText(HomePageRealtor.this, "Successful Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}