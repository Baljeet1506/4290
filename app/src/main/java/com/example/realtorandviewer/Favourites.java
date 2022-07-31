package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Favourites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        // Initialize and assign variable
        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        navigationBarView.setSelectedItemId(R.id.favouritesPage);

        // Perform item selected listener
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.realtorsPage:
                        startActivity(new Intent(getApplicationContext(), FindRealtor.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.propertiesPage:
                        startActivity(new Intent(getApplicationContext(), FindProperties.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.favouritesPage:
                        startActivity(new Intent(getApplicationContext(), Favourites.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.calculatorPage:
                        startActivity(new Intent(getApplicationContext(), MortgageCalculator.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.profilePage:
                        if (Login.uType == 1) {
                            startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        } else {
                            startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        return true;
                }
                return false;
            }
        });
    }
}