package com.example.assigment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import entities.Account;
import utlis.PreferenceUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * create an instance of this fragment.
 */
public class Fragment_Profile extends Fragment implements View.OnClickListener{
    public Fragment_Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageButton btnMyProfile = (ImageButton) view.findViewById(R.id.btnMyProfile);
        btnMyProfile.setOnClickListener(this);
        final ImageButton logOut = (ImageButton) view.findViewById(R.id.btnLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut_Onclick(view);
            }
        });
        return view;
    }
    public void onAttach(Context context){
        super.onAttach(context);
    }
    public void logOut_Onclick(View view){
        PreferenceUtils.saveUserName(null,getContext());
        PreferenceUtils.savePassword(null,getContext());
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMyProfile:
                Intent intent = new Intent(getActivity(),EditProfile.class);
                startActivity(intent);
                break;
        }
    }
}