package com.example.swipeup_frontend;

import java.util.List;

public class Home_Model {

    public List<Home_Model_List> getVideos() {
        return videos;
    }

    public void setVideos(List<Home_Model_List> videos) {
        this.videos = videos;
    }

    public Home_Model(List<Home_Model_List> videos) {
        this.videos = videos;
    }

    List<Home_Model_List> videos;





}
