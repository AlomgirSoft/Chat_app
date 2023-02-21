package com.bdcit.mymasssenger;

import static com.bdcit.mymasssenger.Utils.ShowAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.bdcit.mymasssenger.databinding.ActivityRegistration2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity2 extends AppCompatActivity {

    ActivityRegistration2Binding binding;

    FirebaseAuth firebaseAuth;

    ProgressDialog dialog;


    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistration2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth= FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("user");

        dialog = new ProgressDialog(RegistrationActivity2.this);
        dialog.setMessage("Please wait...");




        binding.singup.setOnClickListener(v->{

            String name1, email, phone,password;


            name1 =binding.name1.getText().toString();
            email= binding.email1.getText().toString();
            phone= binding.phone1.getText().toString();
            password= binding.passwordedt1.getText().toString();


            if (name1.equals("")){
                ShowAlert(RegistrationActivity2.this,"Name files can't be empty!!");
            }else if (email.equals("")){
                ShowAlert(RegistrationActivity2.this,"Email files can't be empty!!");
            }else if (phone.equals("")){
                ShowAlert(RegistrationActivity2.this,"Phone files can't be empty!!");
            }else if (password.equals("")){
                ShowAlert(RegistrationActivity2.this,"Phone files can't be empty!!");
            }else {
                CreateAccount(email,password,name1,phone);
            }
        });





    }

    private void CreateAccount(String email, String password,String name,String phone) {

        dialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

                    String user = firebaseUser.getUid();


                    HashMap<String , Object>hashMap= new HashMap<>();
                    hashMap.put("userNmae",name);
                    hashMap.put("userEmail",email);
                    hashMap.put("userPhone",phone);
                    hashMap.put("profileImage","ima");
                    hashMap.put("userId",user);



                    databaseReference.child(user).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {



                            dialog.dismiss();
                            Intent intent = new Intent(RegistrationActivity2.this,ImageUploadActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            ShowAlert(RegistrationActivity2.this,e.getLocalizedMessage());

                        }
                    });


                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                ShowAlert(RegistrationActivity2.this,e.getMessage().toString());
                dialog.dismiss();

            }
        });
    }
}