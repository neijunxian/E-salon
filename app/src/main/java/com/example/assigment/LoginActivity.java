package com.example.assigment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import database.AccountDB;
import entities.Account;
import utlis.PreferenceUtils;

public class LoginActivity extends AppCompatActivity {
    ImageView backButton;
    private Button buttonLogin;
    private EditText editTextUserName, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.loginButton);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonLogin_onClick(view);
            }
        });
    }


    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void buttonLogin_onClick(View view) {
        AccountDB accountDB = new AccountDB(getApplicationContext());
        String username = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        Account account = accountDB.login(username, password);
        if (account == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.invalid_account);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        } else {
            PreferenceUtils.saveUserName(username,this);
            PreferenceUtils.savePassword(password,this);
            Intent Intent = new Intent(this, DashboardActivity.class);
            Intent.putExtra("account", account);
            startActivity(Intent);
            finish();
        }
    }
}