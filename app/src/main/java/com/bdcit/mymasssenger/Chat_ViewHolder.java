package com.bdcit.mymasssenger;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class Chat_ViewHolder extends RecyclerView.ViewHolder {


    public   TextView textView;
    public Chat_ViewHolder(@NonNull View itemView) {
        super(itemView);

        textView= itemView.findViewById(R.id.chatTv);
    }
}
