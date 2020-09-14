package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

public class ServiceDetail extends AppCompatActivity {


    private TextView mchangeText;
    private String s1,s2,s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        /*Toolbar toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Service Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        s1 =  getResources().getString(R.string.haircut1);
        s2=  getResources().getString(R.string.haircut2);
        s3=   getResources().getString(R.string.haircut3);
        mchangeText = findViewById(R.id.detail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    public void changeText(View view) {
        mchangeText.setText(s1);
    }

    public void changeText2(View view) {
        mchangeText.setText(s2);
    }

    public void changeText3(View view) {
        mchangeText.setText(s3);
    }
}