package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import utlis.PreferenceUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
    }
    private void initViews(){
        loginButton = (Button) findViewById(R.id.loginBtn);
        if(PreferenceUtils.getUserName(this)!=null ){
            Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
            startActivity(intent);
            finish();
        }else{

        }
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    private void initListeners(){
        loginButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginBtn:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}