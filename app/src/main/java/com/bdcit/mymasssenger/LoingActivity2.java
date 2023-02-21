package com.bdcit.mymasssenger;

import static com.bdcit.mymasssenger.Utils.ShowAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bdcit.mymasssenger.databinding.ActivityLoing2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoingActivity2 extends AppCompatActivity {

    ActivityLoing2Binding binding;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoing2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();




        binding.createnewAccount.setOnClickListener(v->{

            Intent intent = new Intent(LoingActivity2.this,RegistrationActivity2.class);
            startActivity(intent);


        });

        binding.fon.setOnClickListener(v->{

            String email = binding.emaillogin.getText().toString();
            String password = binding.passwordlogin.getText().toString();

           createuser(email,password);
        });
    }

    private void createuser(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    startActivity(new Intent(LoingActivity2.this,MainActivity.class));
                    finish();



                }else {
//                    String errosms = task.getException().getLocalizedMessage();
//
//                    ShowAlert(LoingActivity2.this,errosms);
                    ShowAlert(getApplicationContext(),task.getException().getLocalizedMessage());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                ShowAlert(getApplicationContext(),e.getLocalizedMessage());

            }
        });
    }
}