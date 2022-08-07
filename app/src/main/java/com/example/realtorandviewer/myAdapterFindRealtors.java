package com.example.realtorandviewer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapterFindRealtors extends FirebaseRecyclerAdapter<User, myAdapterFindRealtors.myviewholder> {

    public myAdapterFindRealtors(@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final User User) {

        holder.firsName.setText(User.getFirstName());
        holder.lastName.setText(User.getLastName());
        holder.company.setText(User.getCompany());
        holder.phoneNumber.setText(User.getPhNumber());
        holder.email.setText(User.getEmail());
        holder.aboutMe.setText(User.getAboutMe());
        Glide.with(holder.img.getContext()).load(User.getPimage()).into(holder.img);

        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToFavorite(holder.firsName.getContext(),
                        User.getFirstName(),
                        User.getLastName(),
                        User.getCompany(),
                        User.getPhNumber(),
                        User.getEmail(),
                        User.getAboutMe(),
                        User.getPimage(),
                        User.getUserType().toString());
            };
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login.REALTOR_POSITION = getSnapshots().getSnapshot(position).getKey();
                //Toast.makeText(holder.firsName.getContext(), "Position is " + Login.REALTOR_POSITION, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(holder.firsName.getContext(), RealtorProfileDetailView.class);
                holder.firsName.getContext().startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_find_realtors, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView firsName, lastName, company, phoneNumber, email, aboutMe;
        CircleImageView img;
        ImageButton heart;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            firsName = (TextView) itemView.findViewById(R.id.firstname_Text_FindRealtor);
            lastName = (TextView) itemView.findViewById(R.id.lastname_Text_FindRealtor);
            company = (TextView) itemView.findViewById(R.id.company_Text_FindRealtor);
            phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber_Text_FindRealtor);
            email = (TextView) itemView.findViewById(R.id.email_Text_FindRealtor);
            aboutMe = (TextView) itemView.findViewById(R.id.aboutMe_Text_FindRealtor);
            img = (CircleImageView) itemView.findViewById(R.id.user_profile_image);
            heart = itemView.findViewById(R.id.favouriteListingsBtn2);


        }
    }
    public static void addToFavorite(Context context, String firstName, String lastName, String company, String phNumber, String email, String  aboutMe, String pimage, String userType){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            Toast.makeText(context, "Not logged it", Toast.LENGTH_SHORT).show();
        }else {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("firstName", firstName );
            hashMap.put("lastName", lastName );
            hashMap.put("company", company );
            hashMap.put("phNumber", phNumber );
            hashMap.put("email", email );
            hashMap.put("aboutMe", aboutMe );
            hashMap.put("pimage", pimage );
            hashMap.put("userType", userType );

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("RealtorUsers").child("Favourites");
            ref.push().setValue(hashMap);

        }
    }


}

