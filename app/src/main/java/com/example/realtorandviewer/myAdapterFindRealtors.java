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

public class myAdapterFindRealtors extends FirebaseRecyclerAdapter<User, myAdapterFindRealtors.myviewholder> {

    public myAdapterFindRealtors(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final User User) {

        holder.firsName.setText(User.getFirstName());
        holder.lastName.setText(User.getLastName());
        holder.phoneNumber.setText(User.getPhNumber());
        holder.email.setText(User.getEmail());
       // holder.uType.setText(User.getUserType());


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_find_realtors, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView firsName, lastName, phoneNumber, email;
        ImageButton edit;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            firsName = (TextView) itemView.findViewById(R.id.firstname_Text_FindRealtor);
            lastName = (TextView) itemView.findViewById(R.id.lastname_Text_FindProperties);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber_Text_FindProperties);
            email = (TextView) itemView.findViewById(R.id.email_Text_FindProperties);
            //uType = (TextView) itemView.findViewById(R.id.userType_Text_FindProperties);

            edit = (ImageButton) itemView.findViewById(R.id.editListingBtn);

        }
    }


}
