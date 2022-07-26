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

public class HomePageViewer extends AppCompatActivity {

    private Button logoutBtn;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalcBtn;
    TextView firstNameText, lastNameText, emailText;
    CardView favouritesBtn, recommendedRealtorBtn, resourcesBtn;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_viewer);

        logoutBtn = findViewById(R.id.btnLogout);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        mortgageCalcBtn = findViewById(R.id.btnMortgageCalculator);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);

        favouritesBtn = findViewById(R.id.btnFavourites);
        recommendedRealtorBtn = findViewById(R.id.btnRecommendedRealtors);
        resourcesBtn = findViewById(R.id.btnResources);

        firstNameText = findViewById(R.id.textFirstName);
        lastNameText = findViewById(R.id.textLastName);
        emailText = findViewById(R.id.textEmail);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("ViewerUsers");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String email = userProfile.email;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    emailText.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageViewer.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

        favouritesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Favourites.class)));
        recommendedRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RecommendedRealtor.class)));
        resourcesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourcesPageViewer.class)));

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        mortgageCalcBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(HomePageViewer.this, Login.class);
                startActivity(intentL);
                finish();
                Toast.makeText(HomePageViewer.this, "Successful Logout", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



