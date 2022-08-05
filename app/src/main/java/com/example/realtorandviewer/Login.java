package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText editTextEmail, editTextPassword;
    Button btnLogin, btnRegister, btnForgotPassword;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;

    public static int uType = 0;
    public static String uID_ = "";
    public static String str_NEW_Records_Key1 = "";
    public static String str_NEW_Records_Key2 = "";
    public static String str_NEW_Records_Key_PAST_SALE = "";
    public static String MY_LISTING_POSITION = "";
    public static String MY_PAST_SALE_POSITION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegisterRealtor);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegisterRealtor:
                startActivity(new Intent(this, RegisteringType.class));
                break;
            case R.id.btnLogin:
                userLogin();
                break;
            case R.id.btnForgotPassword:
                startActivity(new Intent(this, Forgot.class));
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

                        String uid = task.getResult().getUser().getUid();
                        uID_ = uid;

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        firebaseDatabase.getReference().child("AllUsers").child(uid).child("userType").addListenerForSingleValueEvent(new ValueEventListener() {

                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                int userType = snapshot.getValue(Integer.class);
                                if (userType == 1) {
                                    uType = 1;
                                    startActivity(new Intent(Login.this, HomePageRealtor.class));
                                }
                                if (userType == 2) {
                                    uType = 2;
                                    startActivity(new Intent(Login.this, HomePageViewer.class));
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check your email to verify your account", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}