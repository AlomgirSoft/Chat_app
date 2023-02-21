package com.bdcit.mymasssenger;

import static com.bdcit.mymasssenger.Utils.ShowAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bdcit.mymasssenger.databinding.ActivityChatBinding;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    ActivityChatBinding binding;
    DatabaseReference databaseReference;
      Intent intent;

      Toolbar toolbar;
    String remote_user_Id;
      FirebaseUser firebaseUser;

      List<Chat_Model>chatModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //=========================================================
        toolbar= findViewById(R.id.chatToolber1);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        chatModelList= new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        binding.reycler.setLayoutManager(linearLayoutManager);





        intent = getIntent();

        remote_user_Id= intent.getStringExtra("user_Id");
        databaseReference= FirebaseDatabase.getInstance().getReference();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
//=========================================================
       getRemoteUser(remote_user_Id);
       getChatMessage();




     binding.sentBtn.setOnClickListener(v -> {


         String message= binding.edtmassage.getText().toString().trim();
         String currentUserId=firebaseUser.getUid();
         String massageId=databaseReference.push().getKey();


         Chat_Model chat_model= new Chat_Model(currentUserId,remote_user_Id,message,massageId);



         databaseReference.child("chat").child(massageId).setValue(chat_model).addOnCompleteListener(new OnCompleteListener<Void>() {
             @Override
             public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful()){

                     Toast.makeText(ChatActivity.this, "Done", Toast.LENGTH_SHORT).show();
                     binding.edtmassage.setText("");



                 }
             }
         });








     });


    }

    private void getChatMessage() {

      databaseReference.child("chat").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot snapshot) {


              for ( DataSnapshot dataSnapshot: snapshot.getChildren()){

                  Chat_Model chatModel= dataSnapshot.getValue(Chat_Model.class);
                   if (snapshot.getChildren()!=null){
                       if (chatModel.getSenderId().equals(firebaseUser.getUid())&& chatModel.getRcevaerId().equals(remote_user_Id)||
                               chatModel.getSenderId().equals(remote_user_Id)&&chatModel.getRcevaerId().equals(firebaseUser.getUid())){



                       }


                      chatModelList.add(chatModel);
                  }
              }
              Chat_Adapter adapter = new Chat_Adapter(chatModelList,firebaseUser.getUid());

              binding.reycler.setAdapter(adapter);

          }

          @Override
          public void onCancelled(@NonNull DatabaseError error) {

              ShowAlert(getApplicationContext(),error.getMessage().toString());

          }
      });


    }


    //=========================================================

    private void getRemoteUser(String user_Id) {




        databaseReference.child("user").child(user_Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User_Model user_model= snapshot.getValue(User_Model.class);
                Glide.with(getApplicationContext()) .load(user_model.getProfileImage()).placeholder(R.drawable.profile_upload_image).into(binding.image);
                binding.username.setText(user_model.getUserNmae());
                binding.userEmail.setText(user_model.getUserEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





}