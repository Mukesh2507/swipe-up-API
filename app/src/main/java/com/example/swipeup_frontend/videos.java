package com.example.swipeup_frontend;

import java.lang.reflect.Array;

public class videos {

    String _id;
    String img_url;
    String username;
    String created_at;
    String caption;
    String location;
//    Array hashtags;
//    Array  mentionFriends;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public videos(String _id, String img_url, String username, String created_at, String caption, String location) {
        this._id = _id;
        this.img_url = img_url;
        this.username = username;
        this.created_at = created_at;
        this.caption = caption;
        this.location = location;
//        this.hashtags = hashtags;
//        this.mentionFriends = mentionFriends;
    }

}
