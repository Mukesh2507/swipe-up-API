package com.example.swipeup_frontend.search;

import com.example.swipeup_frontend.bookmark.bookmark_hashtag_model;

import java.util.List;

public class  hashtag_model {

    public hashtag_model(List<bookmark_hashtag_model> hashtags) {
        this.hashtags = hashtags;
    }

    public List<bookmark_hashtag_model> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<bookmark_hashtag_model> hashtags) {
        this.hashtags = hashtags;
    }

    List<bookmark_hashtag_model> hashtags;



//    public hashtag_model(){
//
//    }

}
