package com.example.realtorandviewer;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myAdapterMyPastSalesProfileDetail extends FirebaseRecyclerAdapter<Properties, myAdapterMyPastSalesProfileDetail.myviewholder> {

    public myAdapterMyPastSalesProfileDetail(@NonNull FirebaseRecyclerOptions<Properties> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myAdapterMyPastSalesProfileDetail.myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Properties Properties) {

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
        Glide.with(holder.my_listing_image_slider.getContext()).load(Properties.getListingImage()).into(holder.my_listing_image_slider);

 /*       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login.MY_PAST_SALE_POSITION = getSnapshots().getSnapshot(position).getKey();

                Intent intent = new Intent(holder.houseNumber.getContext(), RealtorPastSaleDetailView.class);
                holder.houseNumber.getContext().startActivity(intent);

            }
        });*/
    }

    @NonNull
    @Override
    public myAdapterMyPastSalesProfileDetail.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_realtor_profile_current_listings, parent, false);
        return new myAdapterMyPastSalesProfileDetail.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView unitNumber, houseNumber, street, city, province, postal, price,
                beds, landSize, baths, floorSize, age, type, title;
        ImageView my_listing_image_slider;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            unitNumber = itemView.findViewById(R.id.uNum_Text);
            houseNumber = itemView.findViewById(R.id.houseNum_Text);
            street = itemView.findViewById(R.id.street_Text);
            city = itemView.findViewById(R.id.city_Text);
            province = itemView.findViewById(R.id.province_Text);
            postal = itemView.findViewById(R.id.postal_Text);
            price = itemView.findViewById(R.id.price_Text);
            beds = itemView.findViewById(R.id.beds_Text);
            landSize = itemView.findViewById(R.id.land_Text);
            baths = itemView.findViewById(R.id.baths_Text);
            floorSize = itemView.findViewById(R.id.floor_Text);
            age = itemView.findViewById(R.id.age_Text);
            type = itemView.findViewById(R.id.type_Text);
            title = itemView.findViewById(R.id.title_Text);

            my_listing_image_slider = itemView.findViewById(R.id.my_listing_image_slider);

        }
    }
}

