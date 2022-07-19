package com.example.realtorandviewer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<Properties, myadapter.myviewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<Properties> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Properties Properties) {

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

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listings_list_item, parent, false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView unitNumber, houseNumber, street, city, province, postal, price, beds, landSize, baths, floorSize, age, type, title;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            unitNumber = (TextView) itemView.findViewById(R.id.uNum_Text);
            houseNumber = (TextView) itemView.findViewById(R.id.houseNum_Text);
            street = (TextView) itemView.findViewById(R.id.street_Text);
            city = (TextView) itemView.findViewById(R.id.city_Text);
            province = (TextView) itemView.findViewById(R.id.province_Text);
            postal = (TextView) itemView.findViewById(R.id.postal_Text);
            price = (TextView) itemView.findViewById(R.id.price_Text);
            beds = (TextView) itemView.findViewById(R.id.beds_Text);
            landSize = (TextView) itemView.findViewById(R.id.land_Text);
            baths = (TextView) itemView.findViewById(R.id.baths_Text);
            floorSize = (TextView) itemView.findViewById(R.id.floor_Text);
            age = (TextView) itemView.findViewById(R.id.age_Text);
            type = (TextView) itemView.findViewById(R.id.type_Text);
            title = (TextView) itemView.findViewById(R.id.title_Text);


        }
    }


}
