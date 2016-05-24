package com.example.liuzhe.myfirebase.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service implements MediaPlayer.OnErrorListener {

    private MediaPlayer mediaPlayer;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(getApplicationContext(),"播放失败", Toast.LENGTH_SHORT).show();
        return false;
    }
}
