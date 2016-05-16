package com.example.liuzhe.myfirebase;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhe on 2016/3/19.
 */
public class UserInfo {

    String email;
    String name;
    String photo;

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserInfo() {

    }

    public UserInfo(String name, String email, String photo) {
        this.email = email;
        this.name = name;
        this.photo = photo;
    }

}
