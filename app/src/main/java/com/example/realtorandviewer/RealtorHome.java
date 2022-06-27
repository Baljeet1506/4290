package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class RealtorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_home);
        Button LogOut = findViewById(R.id.logoutBtn2);
        Button favouriteBtn = findViewById(R.id.favouriteBtn);
       Button myListingsBtn = findViewById(R.id.myListingsBtn);
        Button pastSalesBtn = findViewById(R.id.PastSalesBtn);
       Button resourcesBtn = findViewById(R.id.resourcesBtn);


        ImageButton findRealtorBtn = findViewById(R.id.findRealtorBtn);
        ImageButton mortgageCalBtn = findViewById(R.id.mortgageCalculatorBtn);
        ImageButton findPropertiesBtn = findViewById(R.id.findPropertiesBtn);
        ImageButton profileBtn = findViewById(R.id.profileBtn);
       // ImageButton profileBtn = findViewById(R.id.profileBtn);

       favouriteBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), FavoritePage.class));
        }
    });

        myListingsBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), MyListingsPage.class));
        }
    });

        pastSalesBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getApplicationContext(), MyPastSalesPage.class));
        }
    });
        resourcesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ResourcesPage.class));
            }
        });

        findRealtorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FindRealtor.class));
            }
        });

        mortgageCalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MortgageCalculator.class));
            }
        });

        findPropertiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FindProperties.class));
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), editProfile.class));
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(RealtorHome.this, LoginActvity.class);
                startActivity(intentL);
                finish();
                Toast.makeText(RealtorHome.this, "Successfully logout", Toast.LENGTH_SHORT).show();
            }
        });

}
}