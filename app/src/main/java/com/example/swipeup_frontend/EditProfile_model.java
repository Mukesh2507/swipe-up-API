package com.example.swipeup_frontend;

import io.realm.RealmObject;

public class EditProfile_model extends RealmObject {


    private String name;
    private String username;
    private String website;
    private String bio;
    private  String profile_img_url;

    public EditProfile_model(String name, String username, String website, String bio, String profile_img_url) {
        this.name = name;
        this.username = username;
        this.website = website;
        this.bio = bio;
        this.profile_img_url = profile_img_url;
    }

    public EditProfile_model(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }


    public String getProfile_img_url() {
        return profile_img_url;
    }

    public void setProfile_img_url(String profile_img_url) {
        this.profile_img_url = profile_img_url;
    }
}
