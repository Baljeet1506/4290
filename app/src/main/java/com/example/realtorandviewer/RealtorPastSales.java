package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class RealtorPastSales extends AppCompatActivity {
    ImageButton backBtn;
    RecyclerView recview;
    myadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtor_my_past_sales);
        setTitle("");

        recview = (RecyclerView) findViewById(R.id.recViewMyPastSales);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Properties> options =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.uID_), Properties.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> {
            if (Login.uType == 1) {
                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
            } else
                startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
        });

        FloatingActionButton addPastSaleBtn = findViewById(R.id.addPastSaleBtn);
        addPastSaleBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorAddPastSale.class)));

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
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView = (SearchView)item.getActionView();

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

    private void processsearch(String s){
        FirebaseRecyclerOptions<Properties> options =
                new FirebaseRecyclerOptions.Builder<Properties>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PastSales").orderByChild("type").startAt(s).endAt(s+"\uf8ff"), Properties.class)
                        .build();

        adapter = new myadapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}