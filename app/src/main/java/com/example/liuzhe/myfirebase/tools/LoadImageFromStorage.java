package com.example.liuzhe.myfirebase.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by liuzhe on 2016/5/12.
 */
public class LoadImageFromStorage {
    String dir;
    String fileName;
    Context context;

    public LoadImageFromStorage(Context context, String dir, String fileName) {
        this.context = context;
        this.dir = dir;
        this.fileName = fileName;
    }

    public Bitmap LoadImage() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @NonNull
    private File createFile() {
        File directory = context.getDir(dir, Context.MODE_PRIVATE);
        return new File(directory, fileName);
    }
}
