package com.bdcit.mymasssenger;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_ViewHolder extends RecyclerView.ViewHolder {


  CircleImageView circleImageView;
  ImageView chat;
  TextView name, email;

    public User_ViewHolder(@NonNull View itemView) {
        super(itemView);

        chat =itemView.findViewById(R.id.imagechat);
        circleImageView= itemView.findViewById(R.id.image2);
        name = itemView.findViewById(R.id.name);
        email= itemView.findViewById(R.id.email);


    }
}
