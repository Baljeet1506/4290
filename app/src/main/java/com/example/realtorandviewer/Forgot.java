package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
    Button resetBtn;
    ImageButton backBtn;
    private EditText emailEditText;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        emailEditText = findViewById(R.id.emailReset);
        progressBar = findViewById(R.id.progressBar3);
        resetBtn = findViewById(R.id.btnReset);
        backBtn = findViewById(R.id.btnBack);

        auth = FirebaseAuth.getInstance();

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
                Snackbar.make(view, "Check your email to reset your password", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Login.class)));
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please provide a valid email");
            emailEditText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Toast.makeText(ForgotActivity.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                } else {
                    Toast.makeText(Forgot.this, "Try Again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
