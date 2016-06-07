package com.example.liuzhe.myfirebase.tools;

/**
 * Created by liuzhe on 2016/6/6.
 */
public class ConstUtil {
    //Music接收器所能响应的Action,更新seekbar
    public static final String MUSIC_ACTION = "com.example.liuzhe.myfirebase.MUSIC_ACTION";
    //MusicService接收器所能响应的Action
    public static final String MUSICSERVICE_ACTION = "com.example.liuzhe.myfirebase.MUSICSERVICE_ACTION";
    //初始化flag
    public static final int STATE_NON = 0x122;
    //播放的flag
    public static final int STATE_PLAY = 0x123;
    //暂停的flag
    public static final int STATE_PAUSE = 0x124;
    //停止放的flag
    public static final int STATE_STOP = 0x125;
    //播放上一首的flag
    public static final int STATE_PREVIOUS = 0x126;
    //播放下一首的flag
    public static final int STATE_NEXT = 0x127;
    //菜单关于选项的itemId
    public static final int MENU_ABOUT = 0x200;
    //菜单退出选的项的itemId
    public static final int MENU_EXIT = 0x201;
    // 设置seekbar长度
    public static final int SEEKBAR_DURATION = 0x300;
    // 设置seekbar进度
    public static final int SEEKBAR_CURRENT_DURATION = 0x301;

}
