package com.example.swipeup_frontend;

import java.util.List;

public class User {

    String username, name, _id, imgurl;
    UserInfo userinfo;
    String bio, website;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public User(String bio, String website) {
        this.bio = bio;
        this.website = website;
    }

    public String getXyz() {
        return xyz;
    }

    public void setXyz(String xyz) {
        this.xyz = xyz;
    }

    String xyz;

    String followed_time;


    public User(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public User(){

    }




    public User(String id) {
        this.username = username;
        this.name = name;
        this._id = _id;
        this.imgurl = imgurl;
        this.followed_time  = followed_time;
        this.xyz = xyz;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_Id() {
        return _id;
    }

    public void set_Id(String _id) {
        this._id = _id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getFollowed_time() {
        return followed_time;
    }

    public void setFollowed_time(String followed_time) {
        this.followed_time = followed_time;
    }
}
