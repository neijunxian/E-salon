package com.example.assigment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ChangePassword extends AppCompatActivity {
    private EditText editTextOldPassword, editTextNewPassword, editTextConfPassword;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        editTextNewPassword = findViewById(R.id.NewTextPassword);
        editTextConfPassword = findViewById(R.id.confirmPassword);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });
        loadData();
    }

    public void buttonSave_onClick(View view) {

        String pass = editTextNewPassword.getText().toString();
        String cpass = editTextConfPassword.getText().toString();

        if (!cpass.equals(pass)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.error_password);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        } else {


            /*if (accountDB.updatePassword(currentAccount)) {
                PreferenceUtils.savePassword(pass,this);
                Intent intent = new Intent(this,EditProfile.class);
                startActivity(intent);
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.can_not_create);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }*/
        }
        /*try {
            String pass = editTextNewPassword.getText().toString();
            String cpass = editTextConfPassword.getText().toString();
            AccountDB accountDB = new AccountDB(getApplicationContext());
            Account currentAccount = accountDB.find(account.getId());

                if (!cpass.equals(pass)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.error);
                    builder.setMessage(R.string.error_password);
                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                } else {
                    currentAccount.setPassword(pass);
                    if (accountDB.updatePassword(currentAccount)) {
                            Intent intent = new Intent(this,EditProfile.class);
                            startActivity(intent);
                            finish();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                        builder.setTitle(R.string.error);
                        builder.setMessage(R.string.can_not_create);
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.show();
                    }
                }

        }catch (Exception e){
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(e.getMessage());
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();
        }*/
    }

    public void loadData() {
        editTextOldPassword = findViewById(R.id.oldTextPassword);

    }
}