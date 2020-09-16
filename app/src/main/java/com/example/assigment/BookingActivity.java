package com.example.assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.assigment.Modal.Detail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class BookingActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public String text;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private ArrayList<String> arrayList = new ArrayList<>();
    private Spinner spinner;
    private TextView price, hours, ChooseDate, ChooseTime;
    int Hour, Minute;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Booking Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.Spinner);
        price = findViewById(R.id.price);
        hours = findViewById(R.id.hours);
        ChooseDate = findViewById(R.id.date);
        ChooseTime = findViewById(R.id.time);
        showDataSpinner();
        ChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDailog();
            }
        });

        ChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Hour = hourOfDay;
                                Minute = minute;
                                String time = Hour + ":" + Minute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm:aa");
                                    ChooseTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(Hour, Minute);
                timePickerDialog.show();
            }
        });

    }

    private void showDataSpinner() {
        databaseReference.child("Detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    arrayList.add(item.child("pid").getValue(String.class));
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BookingActivity.this, R.layout.style_spinner, arrayList);
                spinner.setAdapter(arrayAdapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        getServiceDetails();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getServiceDetails() {
         text = spinner.getSelectedItem().toString();
        DatabaseReference serviceRef = FirebaseDatabase.getInstance().getReference("Detail");
        serviceRef.child(text).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Detail detail = snapshot.getValue(Detail.class);
                    price.setText(detail.getPrice());
                    hours.setText(detail.getTime());
                    /*servicePrice.setText(detail.getPrice());
                    Picasso.get().load(detail.getPicture()).into(serviceImage);*/
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showDatePickerDailog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(dayOfMonth);
        String year_string = Integer.toString(year);
        String date = day_string + "/" + month_string + "/" + year_string;
        ChooseDate.setText(date);
    }

    public void btnSave(View view) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userUid = firebaseUser.getUid();
        String Date = ChooseDate.getText().toString();
        String Time = ChooseTime.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("BookingId", userUid);
        hashMap.put("BookingService",text);
        hashMap.put("BookingDate",Date);
        hashMap.put("BookingTime",Time);
        reference.child("Booking").push().setValue(hashMap);
        Toast.makeText(BookingActivity.this, "Booking Successful", Toast.LENGTH_LONG).show();
    }


}