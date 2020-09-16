package com.example.assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.assigment.Modal.Booking;
import com.example.assigment.adapter.bookingHistoryAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookingHistoryActivity extends AppCompatActivity {
    private DatabaseReference ServiceRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Booking> myBooking;
    DatabaseReference reference;
    FirebaseUser firebaseUser;
    public String userUid;
    com.example.assigment.adapter.bookingHistoryAdapter bookingHistoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history);
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Booking History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_bookingHistory);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userUid = firebaseUser.getUid();
        ServiceRef = FirebaseDatabase.getInstance().getReference().child("Booking");
        showBookingHistory();
    }

    public void showBookingHistory() {
        myBooking= new ArrayList<>();
        ServiceRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myBooking.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    Booking booking =snapshot1.getValue(Booking.class);
                    if(booking.getBookingId().equals(userUid)){
                        myBooking.add(booking);
                    }
                    bookingHistoryAdapter = new bookingHistoryAdapter(BookingHistoryActivity.this, myBooking);
                    recyclerView.setAdapter(bookingHistoryAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}