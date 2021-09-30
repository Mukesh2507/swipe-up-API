package com.example.swipeup_frontend.addfriends;

import com.example.swipeup_frontend.profile.User;
import com.example.swipeup_frontend.profile.profile_model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class addfriends_model {

    @SerializedName("users")
    List<User> users;
    String response;
    profile_model xyz;


    public addfriends_model(String follower_count, String following_count) {
        this.follower_count = follower_count;
        this.following_count = following_count;
    }

    public String getFollower_count() {
        return follower_count;
    }

    public void setFollower_count(String follower_count) {
        this.follower_count = follower_count;
    }

    public String getFollowing_count() {
        return following_count;
    }

    public void setFollowing_count(String following_count) {
        this.following_count = following_count;
    }

    String follower_count;
    String following_count;

    public addfriends_model(profile_model xyz) {
        this.xyz = xyz;
    }

    public profile_model getXyz() {
        return xyz;
    }

    public void setXyz(profile_model xyz) {
        this.xyz = xyz;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


    public addfriends_model(String response) {
        this.response = response;
    }


//    List<User> hastags;




    public addfriends_model(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
