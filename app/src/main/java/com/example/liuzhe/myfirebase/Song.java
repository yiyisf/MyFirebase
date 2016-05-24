package com.example.liuzhe.myfirebase;

/**
 * Created by liuzhe on 2016/3/19.
 */
public class Song {

    String name;
    String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Song() {

    }

    public Song(String name, String link) {
        try {
            System.out.print("Before" + name);
            this.name = name.replaceAll("\\r|\\n", "");
            this.link = link;
            System.out.print("After" + name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
