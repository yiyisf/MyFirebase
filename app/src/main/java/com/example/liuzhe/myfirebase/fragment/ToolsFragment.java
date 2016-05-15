package com.example.liuzhe.myfirebase.fragment;

/**
 * Created by liuzhe on 2016/5/11.
 */

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liuzhe.myfirebase.MyApp;
import com.example.liuzhe.myfirebase.R;
import com.example.liuzhe.myfirebase.tools.LoadImageFromStorage;
import com.example.liuzhe.myfirebase.tools.SaveToInternalStorage;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.InputStream;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class ToolsFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String TOOLS_AUTHDATA = "authData";

    ProgressDialog pDialog;
    ImageView logo;
    private Bitmap bitmap = null;
    static Uri userUri;
    static String name;
    static String emal;
    static String userId;

    public ToolsFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ToolsFragment newTool(GoogleSignInAccount googleSignInAccount) {
        emal = googleSignInAccount.getEmail();
        name = googleSignInAccount.getDisplayName();
        userUri = googleSignInAccount.getPhotoUrl();
        userId = googleSignInAccount.getId();
        ToolsFragment fragment = new ToolsFragment();
//        Bundle args = new Bundle();
//        args.putString(TOOLS_AUTHDATA, MyApp.getGoogleSignInAccount().getEmail());
//        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tool, container, false);
        logo = (ImageView) rootView.findViewById(R.id.logo);
        final TextView userName = (TextView) rootView.findViewById(R.id.userName);
        TextView userEmal = (TextView) rootView.findViewById(R.id.userEmal);
        userName.setText(name);
        userEmal.setText(emal);
        setImageLogo();

        rootView.findViewById(R.id.camora).setOnClickListener(this);
        rootView.findViewById(R.id.save).setOnClickListener(this);
        rootView.findViewById(R.id.camora).setOnTouchListener(this);
        rootView.findViewById(R.id.save).setOnTouchListener(this);
        rootView.findViewById(R.id.money).setOnTouchListener(this);
        rootView.findViewById(R.id.turn).setOnTouchListener(this);
        rootView.findViewById(R.id.mood).setOnTouchListener(this);
        rootView.findViewById(R.id.setting).setOnTouchListener(this);

//        loadLogo(logo);
//        textView.setText(getString(R.string.section_format, getArguments().getString(TOOLS_AUTHDATA, "username")));
        return rootView;
    }

    private void setImageLogo() {
        if (userUri != null) {
            Uri imurl = userUri;
            String dir = userId + "logo";
            String filename = userUri.toString().replace("/", "_");
            Bitmap loadBitmap = new LoadImageFromStorage(getContext(), dir, filename).LoadImage();
            if (loadBitmap != null) {
                logo.setImageBitmap(loadBitmap);
            } else {
                if (imurl != null) {
                    new LoadImage().execute(imurl);
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
//        Uri imurl = MyApp.getGoogleSignInAccount().getPhotoUrl();
//        if (imurl !=null) {
//            new LoadImage().execute(imurl);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camora:
                try {
                    Animation button_anim = null;
                    button_anim = AnimationUtils.loadAnimation(getContext(), R.anim.button_anim);
                    v.startAnimation(button_anim);
                } catch (Error e) {
                    e.printStackTrace();
                }
                break;
            case R.id.save:
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                v.setBackgroundColor(getContext().getResources().getColor(R.color.button_down));
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                v.setBackgroundColor(getContext().getResources().getColor(R.color.button_background));
                break;
            default:
                break;
        }
        return false;
    }

    private class LoadImage extends AsyncTask<Uri, Void, Bitmap> {
        protected Bitmap doInBackground(Uri... uris) {
            String url = uris[0].toString();
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                bitmap = Bitmap.createScaledBitmap(bitmap, 80, 80, true);
//                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uris[0]);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return bitmap;
            }
        }

        protected void onPostExecute(Bitmap image) {

            if (image != null) {
                logo.setImageBitmap(image);
                SaveLogo(image);


            } else {

                pDialog.dismiss();
                Toast.makeText(getContext(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void SaveLogo(Bitmap image) {
        String dir = userId + "logo";
        String filename = userUri.toString().replace("/", "_");
        SaveToInternalStorage saveLogo = new SaveToInternalStorage(getContext(), dir, filename, image);
        saveLogo.execute();
    }


    private void loadLogo(ImageView logo) {

    }
}
