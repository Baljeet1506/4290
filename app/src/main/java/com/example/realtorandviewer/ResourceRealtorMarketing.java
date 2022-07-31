package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ResourceRealtorMarketing extends AppCompatActivity {
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_realtor_marketing);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageRealtor.class)));
    }
}