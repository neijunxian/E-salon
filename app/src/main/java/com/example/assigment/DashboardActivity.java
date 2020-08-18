package com.example.assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView);
        btnNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, new Fragment_dashboard()).commit();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.action_Home:
                            selectedFragment = new Fragment_dashboard();
                            break;

                        case R.id.action_Mailbox:
                            selectedFragment = new Fragment_message();
                            break;

                        case R.id.action_deal:

                            break;

                        case R.id.action_Profile:
                            selectedFragment = new Fragment_Profile();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_layout
                                    , selectedFragment).commit();


                    return true;
                }
            };


}