package com.bdcit.mymasssenger.fragmaent;

import static com.bdcit.mymasssenger.Utils.ShowAlert;

import android.content.Context;
import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdcit.mymasssenger.ChatActivity;
import com.bdcit.mymasssenger.MainActivity;
import com.bdcit.mymasssenger.R;
import com.bdcit.mymasssenger.UserLisenaer;
import com.bdcit.mymasssenger.User_Adpter;
import com.bdcit.mymasssenger.User_Model;

import com.bdcit.mymasssenger.User_ViewHolder;
import com.bdcit.mymasssenger.databinding.FragmentUserBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment implements UserLisenaer {


    public UserFragment() {
        // Required empty public constructor
    }
FragmentUserBinding binding;
ArrayList<User_Model>user_modelList;
 DatabaseReference databaseReference;
 FirebaseUser firebaseUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUserBinding.inflate(inflater,container,false);

        user_modelList= new ArrayList<>();
           firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        Log.i("TAG", "onCreateView: "+user_modelList.size());
        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren()) {
                     User_Model user_model = ds.getValue(User_Model.class);

                                  user_modelList.add(user_model);




                    Log.i("TAG", "onDataChange: "+user_modelList.size());


                    User_Adpter adpter = new User_Adpter(  requireActivity(),user_modelList, UserFragment.this);

                    binding.recyclerview.setAdapter(adpter);



                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                ShowAlert(getContext(),error.getMessage());

            }
        });










        return binding.getRoot();
    }

    @Override
    public void userClickLisenaer(User_Model user_model) {
        Intent intent = new Intent(getActivity(),ChatActivity.class);
        intent.putExtra("user_Id",user_model.getUserId());

        startActivity(intent);
    }
}