package com.example.realtorandviewer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FindRealtor extends AppCompatActivity {
    RecyclerView recview;
    myAdapterFindRealtors adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_realtor);
        setTitle("Filter by First Name");

        recview = findViewById(R.id.recViewFindRealtors);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RealtorUsers"), User.class)
                        .build();

        adapter = new myAdapterFindRealtors(options);
        recview.setAdapter(adapter);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.realtorsPage);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
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

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    private void processsearch(String s) {

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("RealtorUsers").orderByChild("firstName").startAt(s).endAt(s + "\uf8ff"), User.class)
                        .build();

        adapter = new myAdapterFindRealtors(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}