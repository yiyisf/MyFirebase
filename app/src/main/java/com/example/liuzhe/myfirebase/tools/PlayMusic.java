package com.example.liuzhe.myfirebase.tools;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.liuzhe.myfirebase.R;

import java.io.IOException;

/**
 * Created by liuzhe on 2016/5/24.
 */
public class PlayMusic extends AsyncTask<Void, Void, Boolean> implements MediaPlayer.OnErrorListener{
    MediaPlayer mediaPlayer;
    String url;
    Context context;
    LinearLayoutCompat linearLayoutCompat;
    Boolean isstart;


    @Override
    protected void onProgressUpdate(Void... values) {
        if(!isCancelled()) {
            SeekBar seekBar = (SeekBar) linearLayoutCompat.findViewById(R.id.seek_bar);
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
        }else {
            return;
        }
    }

    public PlayMusic(Context context, String url) {
        this.context = context;
//        this.linearLayoutCompat = linearLayoutCompat;
        this.url = url;
        if (mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }
    }

    @Override
    protected void onCancelled() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
        super.onCancelled();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            Log.d("Music:", url);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(context,"播放失败", Toast.LENGTH_SHORT).show();
        return false;
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public void play(String url){
        mediaPlayer.release();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
