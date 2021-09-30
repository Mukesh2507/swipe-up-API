package com.example.swipeup_frontend;

public class bookmark_hashtag_info_model {

    String _id, name;
    String username;

    String bio;
    String website;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public bookmark_hashtag_info_model(String username) {
        this.username = username;
    }

    public bookmark_hashtag_info_model(){

    }

    public bookmark_hashtag_info_model(String _id, String name) {
        this._id = _id;
        this.name = name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
