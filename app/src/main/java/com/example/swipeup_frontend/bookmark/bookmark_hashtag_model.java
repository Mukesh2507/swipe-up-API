package com.example.swipeup_frontend.bookmark;

import com.example.swipeup_frontend.videos.videos;

public class bookmark_hashtag_model {
    String _id;
    String bookmark_time;
    bookmark_hashtag_info_model hashtaginfo;

    bookmark_hashtag_info_model followeruserinfo;

    public bookmark_hashtag_info_model getFolloweruserinfo() {
        return followeruserinfo;
    }

    public void setFolloweruserinfo(bookmark_hashtag_info_model followeruserinfo) {
        this.followeruserinfo = followeruserinfo;
    }


    videos videoinfo;

    public videos getVideoinfo() {
        return videoinfo;
    }

    public void setVideoinfo(videos videoinfo) {
        this.videoinfo = videoinfo;
    }

    public bookmark_hashtag_model(videos videoinfo) {
        this.videoinfo = videoinfo;
    }

    public bookmark_hashtag_info_model getEffectinfo() {
        return effectinfo;
    }

    public void setEffectinfo(bookmark_hashtag_info_model effectinfo) {
        this.effectinfo = effectinfo;
    }

    bookmark_hashtag_info_model effectinfo;


    public bookmark_hashtag_model(bookmark_hashtag_info_model soundinfo) {
        this.soundinfo = soundinfo;
    }

    public bookmark_hashtag_info_model getSoundinfo() {
        return soundinfo;
    }

    public void setSoundinfo(bookmark_hashtag_info_model soundinfo) {
        this.soundinfo = soundinfo;
    }

    bookmark_hashtag_info_model soundinfo;

    public bookmark_hashtag_model(String _id, String bookmark_time, bookmark_hashtag_info_model hashtaginfo) {
        this._id = _id;
        this.bookmark_time = bookmark_time;
        this.hashtaginfo = hashtaginfo;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBookmark_time() {
        return bookmark_time;
    }

    public void setBookmark_time(String bookmark_time) {
        this.bookmark_time = bookmark_time;
    }

    public bookmark_hashtag_info_model getHashtaginfo() {
        return hashtaginfo;
    }

    public void setHashtaginfo(bookmark_hashtag_info_model hashtaginfo) {
        this.hashtaginfo = hashtaginfo;
    }

    public bookmark_hashtag_model(){

    }

}
