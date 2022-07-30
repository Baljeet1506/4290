package com.example.realtorandviewer;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePageRealtor extends AppCompatActivity {

    private ImageButton btnEditRealtorProfile;
    ImageButton findRealtorBtn, findPropertiesBtn, favouritesBtn, mortgageCalculatorBtn;
    CardView preparingToSellBtn, listingBtn, marketingBtn, theOfferBtn;
    TextView firstNameText, lastNameText, companyText, realtorEmailText, realtorPhoneNumText;
    CardView myListingsBtn, pastSalesBtn;
    CircleImageView user_picture;
    Uri filepath;
    ImageView img;
    Button browse, upload;
    Bitmap bitmap;

    private FirebaseUser user;
    private DatabaseReference reference;
    User userClass;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_realtor);

        btnEditRealtorProfile = findViewById(R.id.btnEditRealtorProfile);

        myListingsBtn = findViewById(R.id.btnMyListings);
        pastSalesBtn = findViewById(R.id.btnPastSales);

        findRealtorBtn = findViewById(R.id.btnFindRealtors);
        findPropertiesBtn = findViewById(R.id.btnFindProperties);
        favouritesBtn = findViewById(R.id.btnMyFavourites);
        mortgageCalculatorBtn = findViewById(R.id.btnMortgageCalculator);

        preparingToSellBtn = findViewById(R.id.btnPreparingToSell);
        listingBtn = findViewById(R.id.btnListing);
        marketingBtn = findViewById(R.id.btnMarketing);
        theOfferBtn = findViewById(R.id.btnTheOffer);

        firstNameText = findViewById(R.id.textFirstName);
        lastNameText = findViewById(R.id.textLastName);
        companyText = findViewById(R.id.textCompany);
        realtorEmailText = findViewById(R.id.textRealtorEmail);
        realtorPhoneNumText = findViewById(R.id.textRealtorPhone);
        user_picture = findViewById(R.id.user_picture);

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
                    String company = userProfile.company;
                    String email = userProfile.email;
                    String phone = userProfile.phNumber;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    companyText.setText(company);
                    realtorEmailText.setText(email);
                    realtorPhoneNumText.setText(phone);
                    Glide.with(user_picture.getContext()).load(userProfile.getPimage()).into(user_picture);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageRealtor.this, "The User info did not load", Toast.LENGTH_SHORT).show();
            }
        });

        myListingsBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorListings.class)));
        pastSalesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorPastSales.class)));

        findRealtorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindRealtor.class)));
        findPropertiesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), FindProperties.class)));
        favouritesBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Favourites.class)));
        mortgageCalculatorBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MortgageCalculator.class)));

        preparingToSellBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorPrepareToSell.class)));
        listingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorListing.class)));
        marketingBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorMarketing.class)));
        theOfferBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ResourceRealtorOffer.class)));


        user_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(HomePageRealtor.this)
                        .setContentBackgroundResource(R.color.transparent)
                        .setContentHolder(new ViewHolder(R.layout.dialog_upload_profile_image))
                        .create();

                View picview = dialogPlus.getHolderView();
                Button browseBtn = picview.findViewById(R.id.btn_browse_realtor_pic);
                Button uploadBtn = picview.findViewById(R.id.btn_upload_realtor_pic);
                dialogPlus.show();

                browseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Dexter.withActivity(HomePageRealtor.this)
                                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                .withListener(new PermissionListener() {
                                    @Override
                                    public void onPermissionGranted(PermissionGrantedResponse response) {
                                        Intent intent = new Intent(Intent.ACTION_PICK);
                                        intent.setType("image/*");
                                        startActivityForResult(Intent.createChooser(intent, "Select Image File"), 1);
                                    }

                                    @Override
                                    public void onPermissionDenied(PermissionDeniedResponse response) {

                                    }

                                    @Override
                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                        token.continuePermissionRequest();
                                    }
                                }).check();
                    }
                });

                uploadBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadtofirebase();
                    }
                });
            }
        });


        btnEditRealtorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(HomePageRealtor.this)
                        .setContentBackgroundResource(R.color.transparent)
                        .setContentHolder(new ViewHolder(R.layout.dialog_content_edit_profile_realtor))
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText first_name = myview.findViewById(R.id.dialog_first_name_r);
                final EditText last_name = myview.findViewById(R.id.dialog_last_name_r);
                final EditText company_name = myview.findViewById(R.id.dialog_company_r);
                final EditText phone_number = myview.findViewById(R.id.dialog_phone_number_r);
                final EditText about_me = myview.findViewById(R.id.dialog_description_r);

                ImageButton logoutBtn = myview.findViewById(R.id.btnLogout_dialog_r);

                Button submit = myview.findViewById(R.id.usubmit_dialog_realtor_profile);

                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userProfile = snapshot.getValue(User.class);

                        if (userProfile != null) {
                            String firstName = userProfile.firstName;
                            String lastName = userProfile.lastName;
                            String company = userProfile.company;
                            String phone = userProfile.phNumber;
                            String description = userProfile.aboutMe;

                            first_name.setText(firstName);
                            last_name.setText(lastName);
                            company_name.setText(company);
                            phone_number.setText(phone);
                            about_me.setText(description);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(HomePageRealtor.this, "The User info did not load", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogPlus.show();

                logoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intentL = new Intent(HomePageRealtor.this, Login.class);
                        startActivity(intentL);
                        finish();
                        Toast.makeText(HomePageRealtor.this, "Successful Logout", Toast.LENGTH_SHORT).show();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("firstName", first_name.getText().toString());
                        map.put("lastName", last_name.getText().toString());
                        map.put("company", company_name.getText().toString());
                        map.put("phNumber", phone_number.getText().toString());
                        map.put("aboutMe", about_me.getText().toString());

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

    private void refreshProfileInfo() {

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String company = userProfile.company;
                    String email = userProfile.email;
                    String phone = userProfile.phNumber;

                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);
                    companyText.setText(company);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (Exception ex) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadtofirebase() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference uploader = storage.getReference("Image1" + new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                dialog.dismiss();
                                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                                db.child("RealtorUsers").child(Login.uID_).child("pimage").setValue(uri.toString());

                                Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), HomePageRealtor.class));
                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        float percent = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        dialog.setMessage("Uploaded :" + (int) percent + " %");
                    }
                });
    }
}