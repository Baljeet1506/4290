package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActvity extends AppCompatActivity {
    EditText email, password;
    RadioGroup radioGroup;
    RadioButton realtorBtn;
    RadioButton viewerBtn;
    Button btnLogin, btnRegister;
    DatabaseHelper DB;
    Button forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);
        realtorBtn = findViewById(R.id.relatorBtn);
        viewerBtn = findViewById(R.id.viewerBtn);
        radioGroup = findViewById(R.id.radioGroup);
        email = findViewById(R.id.editTxtEmail);
        password = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        forgotPassword = findViewById(R.id.forgotPassword);

        DB = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAddress(email);
                String userEmail = email.getText().toString();
                String pass = password.getText().toString();

                if (userEmail.equals("") || pass.equals(""))
                    Toast.makeText(LoginActvity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUserPass = DB.checkEmailPassword(userEmail, pass);
                    if (checkUserPass == true) {
                        Toast.makeText(LoginActvity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        String name = DB.getViewerName(userEmail, pass);
                        Intent intent = new Intent(getApplicationContext(), UserHome.class);
                        intent.putExtra("Viewer Name", name);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActvity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                if (email.getText().toString().equals("") && password.getText().toString().equals("")&& realtorBtn.equals("realtorBtn")){
                    Intent intent = new Intent(LoginActvity.this, RealtorHome.class);
                    startActivity(intent);
                }
                else if (email.getText().toString().equals("") && password.getText().toString().equals("")&& viewerBtn.equals("viewerBtn")){
                    Intent intent = new Intent(LoginActvity.this, UserHome.class);
                    startActivity(intent);
                }
                String RadioButton = "";

                if (realtorBtn.isChecked()) {
                    RadioButton += realtorBtn.getText().toString() ;
                  //  startActivity(new Intent(getApplicationContext(), RealtorHome.class));
                }
                else if (viewerBtn.isChecked()){
                    RadioButton += viewerBtn.getText().toString() ;
                  //  startActivity(new Intent(getApplicationContext(), UserHome.class));
                }
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View view){
                startActivity(new Intent(getApplicationContext(), RegisterActvity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ForgotActivity.class));
            }
        });
    }

    private boolean validateEmailAddress(EditText email){

        String emailInput = email.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this, "Email Validated Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }

        else {
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    }

