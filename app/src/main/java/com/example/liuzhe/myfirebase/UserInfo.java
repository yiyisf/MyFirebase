package com.example.liuzhe.myfirebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhe on 2016/3/19.
 */
public class UserInfo {

    String email;
    String name;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public UserInfo() {

    }

    public UserInfo(String name, String email) {
        this.email = email;
        this.name = name;
    }

}
