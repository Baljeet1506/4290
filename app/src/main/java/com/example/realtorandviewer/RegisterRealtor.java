package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterRealtor extends AppCompatActivity implements View.OnClickListener {

    EditText firstName, lastName, email, password, phNumber;
    Button cancelBtn, registerBtn;
    ImageButton backBtn;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_realtor);

        firstName = findViewById(R.id.editTxtFirstName);
        lastName = findViewById(R.id.editTxtLastName);
        email = findViewById(R.id.editTxtRegEmail);
        password = findViewById(R.id.editTxtRegPassword);
        phNumber = findViewById(R.id.editTextPhNumber);

        registerBtn = findViewById(R.id.btn_RegisterRealtor);
        registerBtn.setOnClickListener(this);

        cancelBtn = findViewById(R.id.btnCancelRealtor);
        cancelBtn.setOnClickListener(this);

        backBtn = findViewById(R.id.btnBack);
        backBtn.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar2);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_RegisterRealtor:
                registerUser();
                break;
            case R.id.btnCancelRealtor:
                startActivity(new Intent(getApplicationContext(), RegisteringType.class));
                break;
            case R.id.btnBack:
                startActivity(new Intent(getApplicationContext(), RegisteringType.class));
                break;
        }
    }

    private void registerUser() {
        String userFirstName = firstName.getText().toString();
        String userLastName = lastName.getText().toString();
        String userEmail = email.getText().toString();
        String userPass = password.getText().toString();
        String userPhone = phNumber.getText().toString();
        Integer userType = 1;

        if (userFirstName.isEmpty()) {
            firstName.setError("First name is required");
            firstName.requestFocus();
            return;
        }
        if (userLastName.isEmpty()) {
            lastName.setError("Last name is required");
            lastName.requestFocus();
            return;
        }
        if (userEmail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if (userPhone.isEmpty()) {
            phNumber.setError("Phone number is required");
            phNumber.requestFocus();
            return;
        }
        if (userPass.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            email.setError("Please provide a valid email");
            email.requestFocus();
            return;
        }
        if (userPass.length() < 6) {
            password.setError("Min password length should be 6 characters");
            password.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            User user = new User(userFirstName, userLastName, userEmail, userPhone, userType);

                            FirebaseDatabase.getInstance().getReference("AllUsers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                FirebaseDatabase.getInstance().getReference("RealtorUsers")
                                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(user);

                                                Toast.makeText(RegisterRealtor.this, "User is registered successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                                startActivity(new Intent(getApplicationContext(), Login.class));
                                            } else {
                                                Toast.makeText(RegisterRealtor.this, "Failed to register - Try again", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterRealtor.this, "Failed to register - Try again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}




