package com.example.liuzhe.myfirebase;

/**
 * Created by liuzhe on 2016/3/19.
 */
public class ChatMessage {

    String message;
    String name;

    public ChatMessage() {

    }

    public ChatMessage(String name, String message) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
