package com.example.swipeup_frontend.home;

public class Home_Model_List {
    String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Home_Model_List_Videoinfo getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(Home_Model_List_Videoinfo videoinfo) {
        this.videoinfo = videoinfo;
    }

    public Home_Model_List() {
    }

    public Home_Model_List(String _id, Home_Model_List_Videoinfo videoinfo) {
        this._id = _id;
        this.videoinfo = videoinfo;
    }

    Home_Model_List_Videoinfo videoinfo;

}
