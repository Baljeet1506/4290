package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class RegisterActvity extends AppCompatActivity {

    EditText firstName, lastName, email, dateOfBirth, password, confirmPassword;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnRegister1;
    EditText date;
    DatePickerDialog datePickerDialog;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actvity);

        firstName = findViewById(R.id.editTxtFirstName);
        lastName = findViewById(R.id.editTxtLastName);

        radioGroup = findViewById(R.id.radioGroup);
        email = findViewById(R.id.editTxtRegEmail);
        password = findViewById(R.id.editTxtRegPassword);
        confirmPassword = findViewById(R.id.editTxtConPassword);
        btnRegister1 = findViewById(R.id.btnRegister1);
        DB = new DatabaseHelper(this);

        date = findViewById(R.id.textDate);
        date.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            datePickerDialog = new DatePickerDialog(RegisterActvity.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // set day of month , month and year value in the edit text
                    date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });

        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userFirstName = firstName.getText().toString();
                String userLastName = lastName.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String userGender = radioButton.getText().toString();
                String userDOB = date.getText().toString();

                String userEmail = email.getText().toString();
                String userPass = password.getText().toString();
                String rePass = confirmPassword.getText().toString();

                if(userEmail.equals("")||userPass.equals("")||rePass.equals("")) {
                    Toast.makeText(RegisterActvity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(userPass.equals(rePass)){
                        Boolean checkEmail = DB.checkEmail(userEmail);
                        if(checkEmail==false){
                            Boolean insert = DB.insertData(userFirstName, userLastName, userGender, userDOB, userEmail, userPass);
                            if(insert==true){
                                Toast.makeText(RegisterActvity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                String name = DB.getViewerName(userEmail, userPass);
                                intent.putExtra("Viewer Name", name);
                                startActivity(intent);
                            }else{
                                Toast.makeText(RegisterActvity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActvity.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActvity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}



