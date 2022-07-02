package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegType extends AppCompatActivity implements View.OnClickListener {

    Button btnRealtor, btnViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_type);

        btnRealtor = findViewById(R.id.btnRealtor);
        btnViewer = findViewById(R.id.btnViewer);

        btnRealtor.setOnClickListener(this);
        btnViewer.setOnClickListener(this);
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