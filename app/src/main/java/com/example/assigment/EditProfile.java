package com.example.assigment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import database.AccountDB;
import entities.Account;
import utlis.PreferenceUtils;

public class EditProfile extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextName,editTextEmail,editTextPhone;
    private Spinner spinnerGender;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        editTextPhone=findViewById(R.id.editTextPhone);
        loadData();
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void loadData(){
        editTextName=findViewById(R.id.FullName);
        editTextEmail=findViewById(R.id.Email);
        AccountDB accountDB = new AccountDB(getApplicationContext());
        String username = PreferenceUtils.getUserName(this);
        String password = PreferenceUtils.getUserName(this);
        account = accountDB.login(username, password);
        if (account!=null) {
            editTextName.setText(account.getFullName());
            editTextEmail.setText(account.getEmail());
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}