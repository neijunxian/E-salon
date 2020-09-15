package com.example.assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assigment.Modal.Detail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

public class ServiceDetail extends AppCompatActivity {

    private ImageView serviceImage;
    private TextView servicePrice, serviceName,serviceTime;
    private String serviceID ="";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        /*Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Service Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        serviceID = getIntent().getStringExtra("pid");

        serviceImage = (ImageView) findViewById(R.id.service_image_details);
        servicePrice = (TextView) findViewById(R.id.service_price_details);
        serviceName = (TextView) findViewById(R.id.service_name_details);
        serviceTime = (TextView) findViewById(R.id.service_time_details);

        getServiceDetails(serviceID);
        
    }

    private void getServiceDetails(String serviceID)
    {
        DatabaseReference serviceRef = FirebaseDatabase.getInstance().getReference("Detail");
        serviceRef.child(serviceID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.exists())
                {
                    Detail detail = snapshot.getValue(Detail.class);
                    serviceName.setText(detail.getWhom());
                    serviceTime.setText(detail.getTime());
                    servicePrice.setText(detail.getPrice());
                    Picasso.get().load(detail.getPicture()).into(serviceImage);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


}
