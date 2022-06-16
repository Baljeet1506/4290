package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActvity extends AppCompatActivity {
    EditText email, password;
    Button btnLogin, btnRegister;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);
        email = findViewById(R.id.editTxtEmail);
        password = findViewById(R.id.editTxtPassword);
        btnLogin = findViewById(R.id.btnRegister1);
        btnRegister = findViewById(R.id.btnRegister);
        DB = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String pass = password.getText().toString();

                if(userEmail.equals("")||pass.equals(""))
                    Toast.makeText(LoginActvity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserPass = DB.checkEmailPassword(userEmail, pass);
                    if(checkUserPass==true){
                        Toast.makeText(LoginActvity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        String name = DB.getViewerName(userEmail, pass);
                        Intent intent  = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Viewer Name", name);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActvity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void ViewRegistrationForm(View v) {
        //launch registration activity
        Intent intent = new Intent(this, RegisterActvity.class);
        startActivity(intent);
    }


}
