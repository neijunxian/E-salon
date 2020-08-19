package com.example.assigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assigment.Modal.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class Fragment_Profile extends Fragment implements View.OnClickListener {

    TextView username;
    ImageView profile_image;
    DatabaseReference reference;
    FirebaseUser firebaseUser;

    public Fragment_Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        username = view.findViewById(R.id.username);
        profile_image = view.findViewById(R.id.profile_image);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                username.setText(user.getUsername());
                if (user.getImageURL().equals("default")) {
                    profile_image.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(Fragment_Profile.this).load(user.getImageURL()).into(profile_image);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ImageButton btnMyProfile = (ImageButton) view.findViewById(R.id.btnMyProfile);
        ImageButton btnChangePasswordPage = (ImageButton) view.findViewById(R.id.btnChangePasswordPage);
        btnMyProfile.setOnClickListener(this);
        btnChangePasswordPage.setOnClickListener(this);
        final ImageButton logOut = (ImageButton) view.findViewById(R.id.btnLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut_Onclick(view);
            }
        });
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void userInfo() {

    }

    public void logOut_Onclick(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnMyProfile:
                Intent intent = new Intent(getActivity(), EditProfile.class);
                startActivity(intent);
                break;
            case R.id.btnChangePasswordPage:
                Intent intent1 = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent1);
                break;
        }
    }
}