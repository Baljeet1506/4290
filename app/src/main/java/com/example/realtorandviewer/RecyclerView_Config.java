package com.example.realtorandviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private listingAdapter mListingAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Listings> listings, List<String> keys){
        mContext = context;
        mListingAdapter = new listingAdapter(listings, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mListingAdapter);
    }

    class ListingItemView extends RecyclerView.ViewHolder{
        private TextView houseNumber;
        private TextView street;
        private TextView city;
        private TextView price;

        private String key;

        public ListingItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).
                    inflate(R.layout.listings_list_item, parent, false));

            houseNumber = (TextView) itemView.findViewById(R.id.houseNumber_txtView);
            street = (TextView) itemView.findViewById(R.id.street_txtView);
            city = (TextView) itemView.findViewById(R.id.city_txtView);
            price = (TextView) itemView.findViewById(R.id.price_txtView);
        }

        public void bind(Listings listings, String key){
            houseNumber.setText(listings.getHouseNumber());
            street.setText(listings.getStreet());
            city.setText(listings.getCity());
            price.setText(listings.getPrice());
        }
    }

    class listingAdapter extends RecyclerView.Adapter<ListingItemView>{
        private List<Listings>myList;
        private List<String> mKeys;

        public listingAdapter(List<Listings> myList, List<String> mKeys) {
            this.myList = myList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public ListingItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ListingItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ListingItemView holder, int position) {
            holder.bind(myList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return myList.size();
        }
    }
}
