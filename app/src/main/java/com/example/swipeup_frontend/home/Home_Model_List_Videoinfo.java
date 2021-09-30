package com.example.swipeup_frontend.home;

public class Home_Model_List_Videoinfo {
    String _id;
    String username;
    String created_at;
    String user_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Home_Model_List_Videoinfo() {
    }

    public Home_Model_List_Videoinfo(String _id, String username, String created_at, String user_id, String img_url, String caption) {
        this._id = _id;
        this.username = username;
        this.created_at = created_at;
        this.user_id = user_id;
        this.img_url = img_url;
        this.caption = caption;
    }

    String img_url;
    String caption;
}
