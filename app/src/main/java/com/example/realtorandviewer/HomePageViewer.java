package com.example.realtorandviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
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

public class HomePageViewer extends AppCompatActivity {

    private ImageButton btnEditViewerProfile;
    CardView preparingToBuyBtn, financePlanningBtn, viewPropertiesBtn, makeAnOfferBtn,
            closingPurchaseBtn;
    TextView firstNameText, lastNameText;

    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_viewer);

        btnEditViewerProfile = findViewById(R.id.btnEditViewerProfile);

        preparingToBuyBtn = findViewById(R.id.btnPreparingToBuy);
        financePlanningBtn = findViewById(R.id.btnFinancePlanning);
        viewPropertiesBtn = findViewById(R.id.btnViewProperties);
        makeAnOfferBtn = findViewById(R.id.btnMakeAnOffer);
        closingPurchaseBtn = findViewById(R.id.btnClosingPurchase);

        firstNameText = findViewById(R.id.textFirstName);
        lastNameText = findViewById(R.id.textLastName);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("ViewerUsers");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageViewer.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profilePage);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.realtorsPage:
                        startActivity(new Intent(getApplicationContext(), FindRealtor.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.propertiesPage:
                        startActivity(new Intent(getApplicationContext(), FindProperties.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.favouritesPage:
                        startActivity(new Intent(getApplicationContext(), Favourites.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.calculatorPage:
                        startActivity(new Intent(getApplicationContext(), MortgageCalculator.class));
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        return true;
                    case R.id.profilePage:
                        if (Login.uType == 1) {
                            startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        } else {
                            startActivity(new Intent(getApplicationContext(), HomePageViewer.class));
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        return true;
                }
                return false;
            }
        });

        preparingToBuyBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPrepareToBuy.class)));
        financePlanningBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPlanFinance.class)));
        viewPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerViewProperties.class)));
        makeAnOfferBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerMakeAnOffer.class)));
        closingPurchaseBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceViewerPurchased.class)));

        btnEditViewerProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(HomePageViewer.this)
                        .setContentBackgroundResource(R.color.transparent)
                        .setContentHolder(new ViewHolder(R.layout.dialog_content_edit_profile_viewer))
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText first_name = myview.findViewById(R.id.dialog_first_name_v);
                final EditText last_Name = myview.findViewById(R.id.dialog_last_name_v);
                ImageButton logoutBtn = myview.findViewById(R.id.btnLogout_dialog_v);

                Button submit = myview.findViewById(R.id.usubmit_dialog_viewer_profile);

                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if (userProfile != null) {
                            String firstName = userProfile.firstName;
                            String lastName = userProfile.lastName;
                            first_name.setText(firstName);
                            last_Name.setText(lastName);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HomePageViewer.this, "The User info did not load", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogPlus.show();

                logoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentL = new Intent(HomePageViewer.this, Login.class);
                        startActivity(intentL);
                        finish();
                        Toast.makeText(HomePageViewer.this, "Successful Logout", Toast.LENGTH_SHORT).show();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("firstName", first_name.getText().toString());
                        map.put("lastName", last_Name.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("ViewerUsers").child(Login.uID_)
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

    private void refreshProfileInfo() {

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageViewer.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



