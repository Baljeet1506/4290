package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RegisteringType extends AppCompatActivity implements View.OnClickListener {

    Button btnRealtor, btnViewer;
    ImageButton BackToLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_type);

        btnRealtor = findViewById(R.id.btnRealtor);
        btnViewer = findViewById(R.id.btnViewer);

        btnRealtor.setOnClickListener(this);
        btnViewer.setOnClickListener(this);

        BackToLoginBtn = findViewById(R.id.registerBackBtn);

        BackToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
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