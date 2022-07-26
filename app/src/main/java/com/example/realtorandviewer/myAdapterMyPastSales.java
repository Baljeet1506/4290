package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class myAdapterMyPastSales extends FirebaseRecyclerAdapter<Properties, myAdapterMyPastSales.myviewholder> {

    public myAdapterMyPastSales(@NonNull FirebaseRecyclerOptions<Properties> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Properties Properties) {

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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(holder.houseNumber.getContext(), "Testing the button", Toast.LENGTH_LONG).show();
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.houseNumber.getContext())
                        .setContentBackgroundResource(R.color.transparent)
                        .setContentHolder(new ViewHolder(R.layout.dialog_content_my_past_sales))
                        .setExpanded(true, 1400)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText unitNumber = myview.findViewById(R.id.uNumber_Dialog_Past);
                final EditText houseNumber = myview.findViewById(R.id.hNumber_Dialog_Past);
                final EditText street = myview.findViewById(R.id.street_Dialog_Past);
                final EditText city = myview.findViewById(R.id.city_Dialog_Past);
                final EditText province = myview.findViewById(R.id.province_Dialog_Past);
                final EditText postal = myview.findViewById(R.id.postal_Dialog_Past);
                final EditText price = myview.findViewById(R.id.price_Dialog_Past);
                final EditText beds = myview.findViewById(R.id.beds_Dialog_Past);
                final EditText baths = myview.findViewById(R.id.baths_Dialog_Past);
                final EditText landSize = myview.findViewById(R.id.landSize_Dialog_Past);
                final EditText floorSize = myview.findViewById(R.id.floorSize_Dialog_Past);
                final EditText type = myview.findViewById(R.id.type_Dialog_Past);
                final EditText age = myview.findViewById(R.id.age_Dialog_Past);
                final EditText title = myview.findViewById(R.id.title_Dialog_Past);

                Button submit = myview.findViewById(R.id.usubmit_Dialog_Past);

                unitNumber.setText(Properties.getUnitNumber());
                houseNumber.setText(Properties.getHouseNumber());
                street.setText(Properties.getStreet());
                city.setText(Properties.getCity());
                province.setText(Properties.getProvince());
                postal.setText(Properties.getPostal());
                price.setText(Properties.getPrice());
                beds.setText(Properties.getBeds());
                baths.setText(Properties.getBaths());
                landSize.setText(Properties.getLandSize());
                floorSize.setText(Properties.getFloorSize());
                type.setText(Properties.getType());
                age.setText(Properties.getAge());
                title.setText(Properties.getTitle());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("unitNumber", unitNumber.getText().toString());
                        map.put("houseNumber", houseNumber.getText().toString());
                        map.put("Street", street.getText().toString());
                        map.put("City", city.getText().toString());
                        map.put("Province", province.getText().toString());
                        map.put("Postal", postal.getText().toString());
                        map.put("Price", price.getText().toString());
                        map.put("Beds", beds.getText().toString());
                        map.put("Baths", baths.getText().toString());
                        map.put("LandSize", landSize.getText().toString());
                        map.put("Floor", floorSize.getText().toString());
                        map.put("Type", type.getText().toString());
                        map.put("Age", age.getText().toString());
                        map.put("Title", title.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.uID_)
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.houseNumber.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to remove this?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("PastSales").child(Login.uID_)
                                .child(getRef(position).getKey()).removeValue();
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
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_realtorview_listings, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView unitNumber, houseNumber, street, city, province, postal, price, beds, landSize, baths, floorSize, age, type, title;
        ImageButton edit, delete;

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

            edit = (ImageButton) itemView.findViewById(R.id.editListingBtn);
            delete = (ImageButton) itemView.findViewById(R.id.deleteListingBtn);

        }
    }
}
