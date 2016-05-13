package com.example.liuzhe.myfirebase.tools;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liuzhe on 2016/5/12.
 */
public class SaveToInternalStorage extends AsyncTask<Void, Void, Boolean> {

    Context context;
    String dir;
    String filename;
    Bitmap bitmap;

    public SaveToInternalStorage(Context context, String dir, String filename, Bitmap bitmap) {
        this.context = context;
        this.dir = dir;
        this.filename = filename;
        this.bitmap = bitmap;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        FileOutputStream fos = null;
//            ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/'dir'
//            File directory = context.getDir(dir, Context.MODE_PRIVATE);
//            // Create imageDir
//            File file = new File(directory, filename);


        try {
            fos = new FileOutputStream(createFile());
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return createFile().exists();
    }

    @Override
    protected void onPostExecute(Boolean reult) {

        if (reult) {
            Toast.makeText(context, "保存成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "保存失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    private File createFile() {
        File directory = context.getDir(dir, Context.MODE_PRIVATE);
        return new File(directory, filename);
    }
}
