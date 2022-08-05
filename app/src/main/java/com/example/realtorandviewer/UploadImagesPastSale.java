package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class UploadImagesPastSale extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    Button past_sale_image_browse_btn, past_sale_image_upload_btn, past_sale_image_done_btn;
    TextView alert;
    ArrayList<Uri> ImageList = new ArrayList<Uri>();
    private Uri ImageUri;
    private ProgressDialog progressDialog;
    private int upload_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images_past_sale);

        past_sale_image_browse_btn = findViewById(R.id.past_sale_image_browse_btn);
        past_sale_image_upload_btn = findViewById(R.id.past_sale_image_upload_btn);
        past_sale_image_done_btn = findViewById(R.id.past_sale_image_done_btn);
        alert = findViewById(R.id.alert_upload_image_past_sale);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Image uploading please wait...");

        past_sale_image_browse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMAGE);

            }
        });

        past_sale_image_upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();

                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("PastSaleImages");

                for (upload_count = 0; upload_count < ImageList.size(); upload_count++) {

                    Uri IndividualImage = ImageList.get(upload_count);
                    StorageReference ImageName = ImageFolder.child("Image" + IndividualImage.getLastPathSegment());

                    ImageName.putFile(IndividualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String url = String.valueOf(uri);

                                    StoreLink(url);

                                    StoreSingleLink(url);
                                }
                            });
                        }
                    });
                }
            }
        });

        past_sale_image_done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadImagesPastSale.this, HomePageRealtor.class));
            }
        });
    }

    private void StoreLink(String url) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.uID_).child(Login.str_NEW_Records_Key_PAST_SALE).child("Images");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("url", url);
        hashMap.put("title", "test");

        databaseReference.push().setValue(hashMap);


        progressDialog.dismiss();
        alert.setText("Image Uploaded Successfully");
        past_sale_image_upload_btn.setVisibility(View.GONE);

        ImageList.clear();

    }

    private void StoreSingleLink(String url) {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("PastSales").child(Login.uID_).child(Login.str_NEW_Records_Key_PAST_SALE).child("listingImage").setValue(url);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {

            if (data.getClipData() != null) {

                int countClipData = data.getClipData().getItemCount();

                int currentImageSelected = 0;

                while (currentImageSelected < countClipData) {

                    ImageUri = data.getClipData().getItemAt(currentImageSelected).getUri();

                    ImageList.add(ImageUri);

                    currentImageSelected = currentImageSelected + 1;

                }

                alert.setVisibility(View.VISIBLE);
                alert.setText("You have selected " + ImageList.size() + " images");
                past_sale_image_browse_btn.setVisibility(View.GONE);

            } else {

                Toast.makeText(UploadImagesPastSale.this, "Select Images", Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}