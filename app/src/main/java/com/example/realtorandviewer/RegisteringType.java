package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class RegisteringType extends AppCompatActivity implements View.OnClickListener {
    Button realtorBtn, viewerBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_type);

        backBtn = findViewById(R.id.btnBack);
        realtorBtn = findViewById(R.id.btnRealtor);
        viewerBtn = findViewById(R.id.btnViewer);

        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Login.class)));
        realtorBtn.setOnClickListener(this);
        viewerBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRealtor:
                startActivity(new Intent(this, RegisterRealtor.class));
                break;
            case R.id.btnViewer:
                startActivity(new Intent(this, RegisterViewer.class));
                break;
        }
    }
}