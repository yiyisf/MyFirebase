package com.example.liuzhe.myfirebase.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.liuzhe.myfirebase.MusicViewHolder;
import com.example.liuzhe.myfirebase.MyApp;
import com.example.liuzhe.myfirebase.R;
import com.example.liuzhe.myfirebase.Song;
import com.example.liuzhe.myfirebase.fragment.MusicFragment;
import com.example.liuzhe.myfirebase.tools.ConstUtil;
import com.example.liuzhe.myfirebase.tools.MusicRecyclerAdapter;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service implements MediaPlayer.OnErrorListener {

    final String TAG = "Service";
    public static MediaPlayer mediaPlayer;
    public static boolean isChanging = false;//互斥变量，防止定时器与SeekBar拖动时进度冲突
    Timer mTimer;
    TimerTask mTimerTask;
    MusicRecyclerAdapter musicRecyclerAdapter;
    //当前播放状态
    int state = ConstUtil.STATE_NON;
    //记录Timer运行状态
    boolean isTimerRunning = false;
    int currentposition = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Created. ");
        MusicSercieReceiver receiver = new MusicSercieReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConstUtil.MUSICSERVICE_ACTION);
        registerReceiver(receiver, filter);
        musicRecyclerAdapter = new MusicRecyclerAdapter(Song.class, R.layout.fragment_song_list,
                MusicViewHolder.class, MyApp.getFirebase().child("music"));

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mTimer.cancel();
                ++currentposition;
                prepareAndPlay(currentposition);
//                mp.release();
            }
        });
    }

    /**
     * 装载和播放音乐
     *
     * @param index int index 播放第几首音乐的索引
     */
    protected void prepareAndPlay(int index) {
        // TODO Auto-generated method stub
        if (isTimerRunning) {//如果Timer正在运行
            mTimer.cancel();//取消定时器
            isTimerRunning = false;
        }
        if (index > musicRecyclerAdapter.getItemCount() - 1) {
            index = 0;
        }

        if (index < 0) {
            index = musicRecyclerAdapter.getItemCount() - 1;
        }

        //发送广播停止前台Activity更新界面
//        Intent intent = new Intent();
//        intent.putExtra("current", index);
//        intent.setAction(ConstUtil.MUSIC_ACTION);
//        sendBroadcast(intent);
        try {
            Log.i(TAG, "service play link is:" + musicRecyclerAdapter.getItem(index).getLink());
            mediaPlayer.reset();//初始化mediaPlayer对象
            mediaPlayer.setDataSource(musicRecyclerAdapter.getItem(index).getLink());
            //准备播放音乐
            mediaPlayer.prepare();
            //播放音乐
            mediaPlayer.start();
            //getDuration()方法要在prepare()方法之后，否则会出现Attempt to call getDuration without a valid mediaplayer异常
//            MusicBox.skbMusic.setMax(mediaPlayer.getDuration());//设置SeekBar的长度
            //发送广播停止前台Activity更新界面
            sendBroadcastToMusic(ConstUtil.SEEKBAR_DURATION);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //----------定时器记录播放进度---------//
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                isTimerRunning = true;
                if (isChanging == true)//当用户正在拖动进度进度条时不处理进度条的的进度
                    return;
                sendBroadcastToMusic(ConstUtil.SEEKBAR_CURRENT_DURATION);
            }
        };

        mTimer.schedule(mTimerTask, 0, 10);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Toast.makeText(getApplicationContext(), "播放失败", Toast.LENGTH_SHORT).show();
        return false;
    }


    private class MusicSercieReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "receved");
            int contrl = intent.getIntExtra("contrl", -1);
            int index = intent.getIntExtra("positon", 0);
            if (index != currentposition && index != 0) {
                currentposition = index;
            }
            switch (contrl) {
                case ConstUtil.STATE_PLAY:
                    Log.i(TAG, "Service play index" + String.valueOf(index));
                    if (state == ConstUtil.STATE_PAUSE) {
                        mediaPlayer.start();
                    } else {
                        prepareAndPlay(index);
                    }
                    state = ConstUtil.STATE_PLAY;
                    break;
                case ConstUtil.STATE_PAUSE:
                    if (state == ConstUtil.STATE_PLAY) {
                        mediaPlayer.pause();
                        state = ConstUtil.STATE_PAUSE;
                        Log.i(TAG, "serveice is stop");
                    }
                    break;
                case ConstUtil.STATE_PREVIOUS:
                    prepareAndPlay(--currentposition);
                    state = ConstUtil.STATE_PLAY;
                    break;
                case ConstUtil.STATE_NEXT:
                    prepareAndPlay(++currentposition);
                    Log.i(TAG, "Service next play index" + String.valueOf(currentposition));
                    state = ConstUtil.STATE_PLAY;
                    break;
                default:
                    break;
            }
        }
    }

    private void sendBroadcastToMusic(int state) {
        Intent intent = new Intent();
        intent.setAction(ConstUtil.MUSIC_ACTION);
        intent.putExtra("musicCtrl", state);
        if (state == ConstUtil.SEEKBAR_DURATION) {
            intent.putExtra("max", mediaPlayer.getDuration());
        } else if (state == ConstUtil.SEEKBAR_CURRENT_DURATION) {
            intent.putExtra("current", mediaPlayer.getCurrentPosition());
        }
        sendBroadcast(intent);
    }
}
