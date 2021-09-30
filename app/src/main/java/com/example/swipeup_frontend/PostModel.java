package com.example.swipeup_frontend;

public class PostModel {
    String title,vUrl,UserEmail;

    public PostModel() {
    }

    public PostModel(String title, String vUrl, String userEmail) {
        this.title = title;
        this.vUrl = vUrl;
        UserEmail = userEmail;
    }

    /*public PostModel(String title, String vUrl) {
            this.title = title;
            this.vUrl = vUrl;
        }
    */

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getvUrl() {
        return vUrl;
    }

    public void setvUrl(String vUrl) {
        this.vUrl = vUrl;
    }
}
