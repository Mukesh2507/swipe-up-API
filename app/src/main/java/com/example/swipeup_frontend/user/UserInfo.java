package com.example.swipeup_frontend.user;

public class UserInfo {


    public UserInfo(String purl) {
        this.purl = purl;
    }

    String purl;

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    String username;
    String name;
    String _id;
    String imgurl;

    public UserInfo(){

    }

    public UserInfo(String username, String name, String _id, String imgurl) {
        this.username = username;
        this.name = name;
        this._id = _id;
        this.imgurl = imgurl;
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

}
