package com.example.liuzhe.myfirebase;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by liuzhe on 2016/3/17.
 */
public class MyApp extends android.app.Application {

    public static Firebase firebase;
    public GoogleApiClient mGoogleApiClient;
    public static GoogleSignInOptions googleSignInOptions;

    public static GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }

    public static void setGoogleSignInAccount(GoogleSignInAccount googleSignInAccount) {
        MyApp.googleSignInAccount = googleSignInAccount;
    }

    public static GoogleSignInAccount googleSignInAccount;

    public static GoogleSignInOptions getGoogleSignInOptions() {
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
        return googleSignInOptions;
    }

    public GoogleApiClient getmGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, getGoogleSignInOptions())
                .build();
        return mGoogleApiClient;
    }

    public static Firebase getFirebase() {
        return firebase;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        try {
            firebase = new Firebase("https://glowing-fire-3217.firebaseio.com/");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (firebase.getAuth() == null || isExpired(firebase.getAuth())) {

            Toast.makeText(this, "未登陆或已超时", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "您已登陆", Toast.LENGTH_SHORT).show();

        }

        Log.i("My app is finish:", "Y");


    }


    private boolean isExpired(AuthData authData) {
        return (System.currentTimeMillis() / 1000 > authData.getExpires());
    }
}
