package com.example.realtorandviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailRealtorListingView extends AppCompatActivity {

    ImageSlider mainslider;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_realtor_listing_view);

        backBtn = findViewById(R.id.btn_back_to_my_listing);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RealtorListings.class)));

        mainslider=(ImageSlider)findViewById(R.id.image_slider_realtor);
        final List<SlideModel> remoteimages=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("MyProperties").child(Login.uID_).child(Login.MY_LISTING_POSITION.toString()).child("Images")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for(DataSnapshot data:dataSnapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("url").getValue().toString(),data.child("title").getValue().toString(), ScaleTypes.FIT));

                        mainslider.setImageList(remoteimages, ScaleTypes.FIT);

                        mainslider.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                Toast.makeText(getApplicationContext(),remoteimages.get(i).getTitle().toString(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}