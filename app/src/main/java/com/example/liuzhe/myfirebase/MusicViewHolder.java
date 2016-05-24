package com.example.liuzhe.myfirebase;

import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by liuzhe on 2016/3/19.
 */
public class MusicViewHolder extends RecyclerView.ViewHolder {

    public TextView song_name;
    public LinearLayoutCompat song_item;

    public MusicViewHolder(View itemView) {
        super(itemView);
        song_item = (LinearLayoutCompat) itemView.findViewById(R.id.song_item);
        song_name = (TextView) itemView.findViewById(R.id.song_name);
    }
}
