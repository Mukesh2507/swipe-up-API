package com.example.swipeup_frontend;

import java.util.List;

public class profileVideoRow {

    private String view;
    private int imgId;

    List<videos> videos;

    public profileVideoRow(List<com.example.swipeup_frontend.videos> videos) {
        this.videos = videos;
    }

    public List<com.example.swipeup_frontend.videos> getVideos() {
        return videos;
    }

    public void setVideos(List<com.example.swipeup_frontend.videos> videos) {
        this.videos = videos;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public profileVideoRow(String view, int imgId) {
        this.view = view;
        this.imgId = imgId;
    }

}
