package com.example.assigment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.assigment.Modal.Detail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import ViewHolder.ServiceViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class Fragment_dashboard extends Fragment implements View.OnClickListener{

    private DatabaseReference ServiceRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    public Fragment_dashboard() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_dashboard, container, false);
        ServiceRef = FirebaseDatabase.getInstance().getReference().child("Detail");
        recyclerView =(RecyclerView) view.findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Detail> options = new FirebaseRecyclerOptions.Builder<Detail>().setQuery(ServiceRef, Detail.class).build();
        FirebaseRecyclerAdapter<Detail, ServiceViewHolder> adapter = new FirebaseRecyclerAdapter<Detail, ServiceViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ServiceViewHolder holder, int position, @NonNull Detail model)
            {
                holder.txtServiceName.setText(model.getWhom());
                Picasso.get().load(model.getPicture()).into(holder.imageView);

            }

            @NonNull
            @Override
            public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dashboard, parent, false);
                ServiceViewHolder holder= new ServiceViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onClick(View v) {

    }
}