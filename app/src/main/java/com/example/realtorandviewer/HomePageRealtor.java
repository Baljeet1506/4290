package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class HomePageRealtor extends AppCompatActivity {

    private ImageButton logoutBtn, btnEditRealtorProfile;
    ImageButton findRealtorBtn, findPropertiesBtn, mortgageCalculatorBtn;
    TextView firstNameText, lastNameText, realtorEmailText, realtorPhoneNumText;
    CardView favouritesBtn, myListingsBtn, pastSalesBtn, resourcesBtn;

    private FirebaseUser user;
    private DatabaseReference reference;
    User userClass;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_realtor);

        logoutBtn = findViewById(R.id.btnLogout);
        btnEditRealtorProfile = findViewById(R.id.btnEditRealtorProfile);

        favouritesBtn = findViewById(R.id.btnFavourites);
        myListingsBtn = findViewById(R.id.btnMyListings);
        pastSalesBtn = findViewById(R.id.btnPastSales);
        resourcesBtn = findViewById(R.id.btnResources);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);

        firstNameText = findViewById(R.id.textFirstName);
        lastNameText = findViewById(R.id.textLastName);
        realtorEmailText = findViewById(R.id.textRealtorEmail);
        realtorPhoneNumText = findViewById(R.id.textRealtorPhone);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("RealtorUsers");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String email = userProfile.email;
                    String phone = userProfile.phNumber;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    realtorEmailText.setText(email);
                    realtorPhoneNumText.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageRealtor.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

        favouritesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Favourites.class)));
        myListingsBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorListings.class)));
        pastSalesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorPastSales.class)));
        resourcesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourcesPageRealtor.class)));

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentL = new Intent(HomePageRealtor.this, Login.class);
                startActivity(intentL);
                finish();
                Toast.makeText(HomePageRealtor.this, "Successful Logout", Toast.LENGTH_SHORT).show();
            }
        });

        btnEditRealtorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(HomePageRealtor.this)
                        .setContentBackgroundResource(R.color.transparent)
                        .setContentHolder(new ViewHolder(R.layout.dialog_content_edit_profile))
                        .setExpanded(true, 1400)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText first_name = myview.findViewById(R.id.dialog_first_name_r);
                final EditText last_Name = myview.findViewById(R.id.dialog_last_name_r);
                final EditText email_ = myview.findViewById(R.id.dialog_email_r);
                final EditText phone_Number = myview.findViewById(R.id.dialog_phone_number_r);
                final EditText description_ = myview.findViewById(R.id.dialog_description_r);

                Button submit = myview.findViewById(R.id.usubmit_dialog_realtor_profile);

                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if (userProfile != null) {
                            String firstName = userProfile.firstName;
                            String lastName = userProfile.lastName;
                            String email = userProfile.email;
                            String phone = userProfile.phNumber;
                            String description = userProfile.aboutMe;

                            first_name.setText(firstName);
                            last_Name.setText(lastName);
                            email_.setText(email);
                            phone_Number.setText(phone);
                            description_.setText(description);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HomePageRealtor.this, "The User info did not load", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("firstName", first_name.getText().toString());
                        map.put("lastName", last_Name.getText().toString());
                        map.put("email", email_.getText().toString());
                        map.put("phNumber", phone_Number.getText().toString());
                        map.put("aboutMe", description_.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("RealtorUsers").child(Login.uID_)
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        FirebaseDatabase.getInstance().getReference().child("AllUsers").child(Login.uID_)
                                                .updateChildren(map);
                                        refreshProfileInfo();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });
    }

    private void refreshProfileInfo(){

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String email = userProfile.email;
                    String phone = userProfile.phNumber;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    realtorEmailText.setText(email);
                    realtorPhoneNumText.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageRealtor.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });
    }


}