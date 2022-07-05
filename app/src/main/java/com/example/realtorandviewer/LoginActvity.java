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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActvity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword;
    RadioGroup radioGroup;
    RadioButton realtorBtn, viewerBtn;
    Button btnLogin, btnRegister1, forgotPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);
        //  realtorBtn = findViewById(R.id.relatorBtn);
        // viewerBtn = findViewById(R.id.viewerBtn);
        radioGroup = findViewById(R.id.radioGroup);
        editTextEmail = findViewById(R.id.editTxtEmail);
        editTextPassword = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister1 = findViewById(R.id.btnRegister1);
        forgotPassword = findViewById(R.id.forgotPassword);

        btnLogin.setOnClickListener(this);
        btnRegister1.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar1);

        mAuth = FirebaseAuth.getInstance();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegister1:
                startActivity(new Intent(this, RegType.class));
                break;
            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotActivity.class));
                break;
        }
    }

    private void userLogin() {

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();


        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Min password length should be 6 characters");
            editTextPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();




                    if (user.isEmailVerified()) {
                      /*  String uid = task.getResult().getUser().getUid();
                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        firebaseDatabase.getReference().child("User").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int userType = snapshot.getValue(Integer.class);
                                if (userType == 1) {
                                    startActivity(new Intent(LoginActvity.this, RealtorHome.class));
                                }
                                if (userType == 2) {
                                    startActivity(new Intent(LoginActvity.this, UserHome.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });*/
                        startActivity(new Intent(LoginActvity.this, UserHome.class));
                    } else {

                        user.sendEmailVerification();
                        Toast.makeText(LoginActvity.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActvity.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}