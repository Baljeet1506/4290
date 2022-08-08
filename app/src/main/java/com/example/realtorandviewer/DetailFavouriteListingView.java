package com.example.realtorandviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class DetailFavouriteListingView extends AppCompatActivity {

    ImageSlider image_slider_favourite;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favourite_listing_view);

        backBtn = findViewById(R.id.btn_back_to_find_prop);
        backBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Favourites.class)));

        image_slider_favourite=(ImageSlider)findViewById(R.id.image_slider_viewer);
        final List<SlideModel> remoteimages=new ArrayList<>();

/*        FirebaseDatabase.getInstance().getReference().child("AllProperties").child(Login.FAVOURITE_LISTING_POSITION.toString()).child("Images")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for(DataSnapshot data:dataSnapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("url").getValue().toString(),data.child("title").getValue().toString(), ScaleTypes.FIT));

                        image_slider_favourite.setImageList(remoteimages, ScaleTypes.FIT);

                        image_slider_favourite.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                Toast.makeText(getApplicationContext(),remoteimages.get(i).getTitle().toString(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });*/

    }
}