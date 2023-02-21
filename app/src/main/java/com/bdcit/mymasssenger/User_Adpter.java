package com.bdcit.mymasssenger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdcit.mymasssenger.fragmaent.UserFragment;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class User_Adpter extends RecyclerView.Adapter<User_ViewHolder> {

  private Context context;
    private ArrayList<User_Model> user_modelList;
     UserLisenaer userLisenaer;

    public User_Adpter( Context  context , ArrayList<User_Model> user_modelList,UserLisenaer userLisenaer) {
       this.context= context;
        this.user_modelList = user_modelList;
        this.userLisenaer= userLisenaer;
    }

    @NonNull
    @Override
    public User_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(context).inflate(R.layout.user_item_view,parent,false);
        return new User_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User_ViewHolder holder, int position) {


       holder.name.setText(user_modelList.get(position).getUserNmae());
       holder.email.setText(user_modelList.get(position).getUserEmail());
        Glide.with(context) .load(user_modelList.get(position).getProfileImage()).placeholder(R.drawable.profile_upload_image).into(holder.circleImageView);

           holder.chat.setOnClickListener(v -> {


               userLisenaer.userClickLisenaer(user_modelList.get(position));
           });






    }

    @Override
    public int getItemCount() {
        return user_modelList.size();
    }
}
