package com.example.liuzhe.myfirebase;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by liuzhe on 2016/3/19.
 */
public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    TextView r_name;
    TextView r_message;

    public ChatMessageViewHolder(View itemView) {
        super(itemView);
        r_name = (TextView) itemView.findViewById(R.id.r_name);
        r_message = (TextView) itemView.findViewById(R.id.r_text);
    }
}
