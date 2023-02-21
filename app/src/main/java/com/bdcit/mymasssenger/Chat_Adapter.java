package com.bdcit.mymasssenger;

import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Chat_Adapter extends RecyclerView.Adapter<Chat_ViewHolder> {

  private List<Chat_Model>chatModelList;
  private String curreantUser;
    private final static int RIGHT=0;
  private final static int LEFT=1;

  View view;

    public Chat_Adapter(List<Chat_Model> chatModelList, String curreantUser) {
        this.chatModelList = chatModelList;
        this.curreantUser = curreantUser;
    }

    @NonNull
    @Override
    public Chat_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType==RIGHT){
            view=LayoutInflater.from(parent.getContext()).inflate(R.layout.right_layout_ui,parent,false);
        }else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.left_layout_ui,parent,false);
        }





          return new Chat_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Chat_ViewHolder holder, int position) {



        holder.textView.setText(chatModelList.get(position).getMessage());


    }

    @Override
    public int getItemCount() {
        return chatModelList.size();
    }


    @Override
    public int getItemViewType(int position) {


        if (chatModelList.get(position).getSenderId().equals(curreantUser)){

            return RIGHT;
        }else {
            return LEFT;
        }

    }
}
