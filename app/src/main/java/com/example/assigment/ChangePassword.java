package com.example.assigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assigment.Modal.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class ChangePassword extends AppCompatActivity {
    private EditText  editTextNewPassword, editTextConfPassword;
    private Button btnSave;
    public String oldpassowrd;
    DatabaseReference reference;
    private FirebaseUser fuser;

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

        final String pass = editTextNewPassword.getText().toString();
        final String cpass = editTextConfPassword.getText().toString();

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
            if (pass.length() < 6 || cpass.length() < 6) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.password_length);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }else{
                final ProgressDialog pd = new ProgressDialog(this);
                pd.setMessage("Uploading");
                pd.show();
                final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider
                        .getCredential(firebaseUser.getEmail(), oldpassowrd);
                firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(cpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
                                    HashMap<String, Object> hashMap = new HashMap<>();

                                    hashMap.put("password", cpass);
                                    reference.updateChildren(hashMap);
                                    pd.dismiss();
                                    Toast.makeText(ChangePassword.this, "successful Updated", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
            }
        }
    }

    public void loadData() {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        final String[] editGender = new String[1];
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                oldpassowrd = user.getPassword();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}