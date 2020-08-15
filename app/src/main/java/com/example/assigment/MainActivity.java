package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import database.AccountDB;
import entities.Account;
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
        AccountDB accountDB = new AccountDB(getApplicationContext());
        String username = PreferenceUtils.getUserName(this);
        String password = PreferenceUtils.getUserName(this);
        Account account = accountDB.login(username, password);
        /*PreferenceUtils.getUserName(this)!=null*/
        if( account!=null){
            Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
            intent.putExtra("account",account);
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