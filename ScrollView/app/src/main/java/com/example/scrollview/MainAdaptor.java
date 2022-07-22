package com.example.scrollview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MainAdaptor extends FirebaseRecyclerAdapter<MainModel, MainAdaptor.myViewHolder> {

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.type.setText(model.getType());
        holder.price.setText(model.getPrice());
        holder.houseNumber.setText(model.getHouseNumber());
        holder.street.setText(model.getStreet());
        holder.city.setText(model.getCity());
        holder.landSize.setText(model.getLandSize());


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdaptor(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        private TextView houseNumber;
        private TextView street;
        private TextView city;
        private TextView price;
        private TextView type;
        private TextView landSize;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            houseNumber = (TextView) itemView.findViewById(R.id.houseNumber_txtView);
            street = (TextView) itemView.findViewById(R.id.street_txtView);
            city = (TextView) itemView.findViewById(R.id.city_txtView);
            price = (TextView) itemView.findViewById(R.id.price_txtView);
            type = (TextView) itemView.findViewById(R.id.type_txtView);
            landSize = (TextView) itemView.findViewById(R.id.landSize_txtView);
        }
}
