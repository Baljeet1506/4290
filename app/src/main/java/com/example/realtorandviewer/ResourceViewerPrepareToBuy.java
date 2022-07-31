package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ResourceViewerPrepareToBuy extends AppCompatActivity {
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_viewer_prepare_to_buy);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));
    }
}