package com.example.assigment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assigment.Modal.User;
import com.example.assigment.Modal.chat;
import com.example.assigment.adapter.MessageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class activity_message_admin extends AppCompatActivity {


    CircleImageView profile_image;
    ImageButton btn_send;
    EditText type_msg;
    TextView username;

    DatabaseReference reference;
    FirebaseUser firebaseUser;
    Intent intent;

    MessageAdapter messageAdapter;
    List<chat> mchat;

    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_admin);

        Toolbar toolbar =findViewById(R.id.Toolbar_mess);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager((linearLayoutManager));

        username = findViewById(R.id.username_mess);
        type_msg = findViewById(R.id.type_msg);
        btn_send = findViewById(R.id.btn_send);
        profile_image =findViewById(R.id.profile_image_mess);

        intent = getIntent();
        final String userid = intent.getStringExtra("id");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        btn_send.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg= type_msg.getText().toString();
                if (!msg.equals("")){
                    sendMsg(firebaseUser.getUid(), userid ,msg);
                }else {
                    Toast.makeText(activity_message_admin.this,"You cant send empty message",Toast.LENGTH_SHORT).show();
                }
                type_msg.setText("");
            }
        }));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User users = snapshot.getValue(User.class);
                username.setText(users.getUsername());
                    if (users.getImageURL().equals("default")) {
                        profile_image.setImageResource(R.mipmap.ic_launcher);
                    } else {
                        Glide.with(activity_message_admin.this).load(users.getImageURL()).into(profile_image);
                    }

                    readMessagges(firebaseUser.getUid(),userid, users.getImageURL());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMsg (String sender,String receiver,String message){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);

        reference.child("chat").push().setValue(hashMap);
    }

    private void readMessagges(final String myid, final String id, final String imageurl){
        mchat =new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chat chat = snapshot.getValue(chat.class);
                    if ((chat.getReceiver().equals(myid) && chat.getSender().equals(id)) || (chat.getReceiver().equals(id) && chat.getSender().equals(myid))) {
                        mchat.add(chat);
                    }

                    messageAdapter = new MessageAdapter(activity_message_admin.this, mchat, imageurl);
                    recyclerView.setAdapter(messageAdapter);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}