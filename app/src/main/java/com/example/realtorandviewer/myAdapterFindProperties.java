package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class myAdapterFindProperties extends FirebaseRecyclerAdapter<Properties, myAdapterFindProperties.myviewholder> {

    public myAdapterFindProperties(@NonNull FirebaseRecyclerOptions<Properties> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Properties Properties) {

        holder.unitNumber.setText(Properties.getUnitNumber());
        holder.houseNumber.setText(Properties.getHouseNumber());
        holder.street.setText(Properties.getStreet());
        holder.city.setText(Properties.getCity());
        holder.province.setText(Properties.getProvince());
        holder.postal.setText(Properties.getPostal());
        holder.price.setText(holder.dollar + Properties.getPrice());
        holder.beds.setText(Properties.getBeds());
        holder.landSize.setText(Properties.getLandSize() + Login.SQFT_);
        holder.baths.setText(Properties.getBaths());
        holder.floorSize.setText(Properties.getFloorSize() + Login.SQFT_);
        holder.age.setText(Properties.getAge());
        holder.type.setText(Login.TYPE_ + Properties.getType());
        holder.title.setText(Login.TITLE_ + Properties.getTitle());
        Glide.with(holder.find_listing_single_image.getContext()).load(Properties.getListingImage()).into(holder.find_listing_single_image);

        holder.favListingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToFavorite(holder.unitNumber.getContext(),
                        Properties.getUnitNumber(),
                        Properties.getHouseNumber(),
                        Properties.getStreet(),
                        Properties.getCity(),
                        Properties.getProvince(),
                        Properties.getPostal(),
                        Properties.getPrice(),
                        Properties.getBeds(),
                        Properties.getLandSize(),
                        Properties.getBaths(),
                        Properties.getFloorSize(),
                        Properties.getAge(),
                        Properties.getType(),
                        Properties.getTitle(),
                        Properties.getListingImage());

                Toast.makeText(holder.houseNumber.getContext(), "Added to favourites", Toast.LENGTH_LONG).show();
                holder.favListingBtn.setVisibility(View.GONE);

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login.MY_LISTING_POSITION = getSnapshots().getSnapshot(position).getKey();

                Map<String, Object> bookmarkMap = (Map<String, Object>) getSnapshots().getSnapshot(position).getValue();
                Login.REALTOR_POSITION = (String) bookmarkMap.get("uID");

                Intent intent = new Intent(holder.houseNumber.getContext(), DetailViewerListingView.class);
                holder.houseNumber.getContext().startActivity(intent);
                Toast.makeText(holder.houseNumber.getContext(), "" + Login.LISTING_UID, Toast.LENGTH_LONG).show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_find_properties, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView unitNumber, houseNumber, street, city, province, postal, price, beds,
                landSize, baths, floorSize, age, type, title;
        ImageButton favListingBtn;
        ImageView find_listing_single_image;
        String dollar = "$";

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            unitNumber = itemView.findViewById(R.id.uNum_Text_FindProperties);
            houseNumber = itemView.findViewById(R.id.houseNum_Text_FindProperties);
            street = itemView.findViewById(R.id.street_Text_FindProperties);
            city = itemView.findViewById(R.id.city_Text_FindProperties);
            province = itemView.findViewById(R.id.province_Text_FindProperties);
            postal = itemView.findViewById(R.id.postal_Text_FindProperties);
            price = itemView.findViewById(R.id.price_Text_FindProperties);
            beds = itemView.findViewById(R.id.beds_Text_FindProperties);
            landSize = itemView.findViewById(R.id.land_Text_FindProperties);
            baths = itemView.findViewById(R.id.baths_Text_FindProperties);
            floorSize = itemView.findViewById(R.id.floor_Text_FindProperties);
            age = itemView.findViewById(R.id.age_Text_FindProperties);
            type = itemView.findViewById(R.id.type_Text_FindProperties);
            title = itemView.findViewById(R.id.title_Text_FindProperties);
            find_listing_single_image = itemView.findViewById(R.id.find_listing_single_image);
            favListingBtn = itemView.findViewById(R.id.favListingBtn);

        }
    }

    public static void addToFavorite(Context context, String unitNumber, String houseNumber, String street, String city, String province, String postal, String price, String beds, String landSize, String baths, String floorSize, String age, String type, String title, String listingImage) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            Toast.makeText(context, "Not logged it", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("unitNumber", unitNumber);
            hashMap.put("houseNumber", houseNumber);
            hashMap.put("street", street);
            hashMap.put("city", city);
            hashMap.put("province", province);
            hashMap.put("postal", postal);
            hashMap.put("price", price);
            hashMap.put("beds", beds);
            hashMap.put("landSize", landSize);
            hashMap.put("baths", baths);
            hashMap.put("floorSize", floorSize);
            hashMap.put("age", age);
            hashMap.put("type", type);
            hashMap.put("title", title);
            hashMap.put("listingImage", listingImage);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("AllUsers").child(Login.uID_).child("Favourites");
            ref.push().setValue(hashMap);

        }
    }
}
