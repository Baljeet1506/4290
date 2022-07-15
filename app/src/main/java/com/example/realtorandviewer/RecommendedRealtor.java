package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class RecommendedRealtor extends AppCompatActivity {
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_recommended_realtor);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), HomePageViewer.class)));
    }
}