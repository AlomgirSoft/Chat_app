package com.bdcit.mymasssenger.fragmaent;

import static com.bdcit.mymasssenger.Utils.ShowAlert;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdcit.mymasssenger.R;
import com.bdcit.mymasssenger.User_Model;
import com.bdcit.mymasssenger.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }
FragmentProfileBinding binding;
    DatabaseReference databaseReference;

    FirebaseUser firebaseUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentProfileBinding.inflate(inflater,container,false);
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId= firebaseUser.getUid();


        databaseReference= FirebaseDatabase.getInstance().getReference("user").child(currentUserId);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User_Model user_model = snapshot.getValue(User_Model.class);


                binding.userName.setText(user_model.getUserNmae());
                binding.userEmail.setText(user_model.getUserEmail());
                binding.userPhone.setText(user_model.getUserPhone());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                ShowAlert(getContext(),error.getMessage().toString());

            }
        });










        return binding.getRoot();


    }
}