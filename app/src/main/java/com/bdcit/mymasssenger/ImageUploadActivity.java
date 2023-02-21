package com.bdcit.mymasssenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.bdcit.mymasssenger.databinding.ActivityImageUploadBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ImageUploadActivity extends AppCompatActivity {


    ActivityImageUploadBinding binding;

    DatabaseReference databaseReference;
    StorageReference storageReference;

    FirebaseUser firebaseUser;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityImageUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        storageReference= FirebaseStorage.getInstance().getReference("Upload");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();


        binding.profileimageUpload.setOnClickListener(v -> {

            ImagePicker.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start(101);




        });
//=============================================================================
        binding.uploadbtn.setOnClickListener(v -> {

            StorageReference reference = storageReference.child("profile- ima"+firebaseUser.getUid());




            reference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imageUri= String.valueOf(uri);
                                HashMap<String , Object> hashMap= new HashMap<>();

                                hashMap.put("profileImage",imageUri);

                                databaseReference.child(firebaseUser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()){

                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            finish();
                                        }

                                    }
                                });



                            }
                        });


                    }

                }
            });




        });
//=============================================================================



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==101 && resultCode== Activity.RESULT_OK){
           uri= data.getData();
           binding.uploadbtn.setVisibility(View.VISIBLE);
            binding.profileimageUpload.setImageURI(uri);

        }




        super.onActivityResult(requestCode, resultCode, data);
    }
}