package com.example.liuzhe.myfirebase.tools;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.liuzhe.myfirebase.MusicViewHolder;
import com.example.liuzhe.myfirebase.MyApp;
import com.example.liuzhe.myfirebase.R;
import com.example.liuzhe.myfirebase.Song;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

/**
 * Created by liuzhe on 2016/6/6.
 */
public class MusicRecyclerAdapter extends FirebaseRecyclerAdapter<Song, MusicViewHolder> {

    public MusicRecyclerAdapter(Class<Song> modelClass, int modelLayout, Class<MusicViewHolder> viewHolderClass, Firebase ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(MusicViewHolder musicViewHolder, Song song, int i) {

    }


}
