package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class myAdapterFavouriteProperty extends FirebaseRecyclerAdapter<Properties, myAdapterFavouriteProperty.myviewholder> {

    public myAdapterFavouriteProperty(@NonNull FirebaseRecyclerOptions<Properties> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myAdapterFavouriteProperty.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Properties Properties) {

        holder.unitNumber.setText(Properties.getUnitNumber());
        holder.houseNumber.setText(Properties.getHouseNumber());
        holder.street.setText(Properties.getStreet());
        holder.city.setText(Properties.getCity());
        holder.province.setText(Properties.getProvince());
        holder.postal.setText(Properties.getPostal());
        holder.price.setText(Properties.getPrice());
        holder.beds.setText(Properties.getBeds());
        holder.landSize.setText(Properties.getLandSize());
        holder.baths.setText(Properties.getBaths());
        holder.floorSize.setText(Properties.getFloorSize());
        holder.age.setText(Properties.getAge());
        holder.type.setText(Properties.getType());
        holder.title.setText(Properties.getTitle());
        Glide.with(holder.find_listing_single_image.getContext()).load(Properties.getListingImage()).into(holder.find_listing_single_image);

        holder.remove_fav_listing_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.houseNumber.getContext());
                builder.setTitle("Remove Favourite?");
                builder.setMessage("Are you sure you want to remove this from Favourites?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("AllUsers").child(Login.uID_).child("Favourites")
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login.FAVOURITE_LISTING_POSITION = getSnapshots().getSnapshot(position).getKey();

                Intent intent = new Intent(holder.houseNumber.getContext(), DetailFavouriteListingView.class);
                holder.houseNumber.getContext().startActivity(intent);
            }
        });*/
    }

    @NonNull
    @Override
    public myAdapterFavouriteProperty.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_favourite, parent, false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder {

        TextView unitNumber, houseNumber, street, city, province, postal, price, beds, landSize,
                baths, floorSize, age, type, title;
        ImageButton remove_fav_listing_btn;
        ImageView find_listing_single_image;

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
            remove_fav_listing_btn = itemView.findViewById(R.id.remove_fav_listing_btn);

        }
    }

}