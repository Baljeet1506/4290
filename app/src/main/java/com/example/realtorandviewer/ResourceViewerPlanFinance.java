package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ResourceViewerPlanFinance extends AppCompatActivity {
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_viewer_plan_finance);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));
    }
}