package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class ResourceViewerViewProperties extends AppCompatActivity {
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_viewer_view_properties);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));
    }
}