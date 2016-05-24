package com.example.liuzhe.myfirebase.tools;

import android.content.Context;
import android.os.AsyncTask;

import java.net.URL;

/**
 * Created by liuzhe on 2016/5/24.
 */
public class PlayMusic extends AsyncTask<Void, Void, Boolean> {
    URL url;


    public PlayMusic(Context context, URL url) {
        this.url = url;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return null;
    }
}
