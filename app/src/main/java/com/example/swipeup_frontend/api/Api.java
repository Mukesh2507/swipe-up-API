package com.example.swipeup_frontend.api;

import android.text.Editable;

import com.example.swipeup_frontend.home.Home_Model;
import com.example.swipeup_frontend.addfriends.addfriends_model;
import com.example.swipeup_frontend.Inbox.video_details;
import com.example.swipeup_frontend.search.hashtag_model;
import com.example.swipeup_frontend.profile.profileVideoRow;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @GET("/find/")
    Call<addfriends_model> find();

    @FormUrlEncoded
    @POST("/findfriends")
    Call<addfriends_model>  searchFriend(
         @Field("username") String username
    );

    @FormUrlEncoded
    @POST("/registration/email12")
    Call<addfriends_model>  email12(
            @Field("emailid") Editable emailid
    );

    @FormUrlEncoded
    @POST("/registration/sendemailOtp")
    Call<addfriends_model>  sendemail(
            @Field("emailid") Editable emailid
    );


    @FormUrlEncoded
    @POST("/otpVerfication")
    Call<addfriends_model>  OtpVerify(
            @Field("user_id") String user_id,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("/registration/username")
    Call<addfriends_model>  username(
            @Field("user_id") String user_id,
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("/registration/pagename")
    Call<addfriends_model>  pagename(
            @Field("user_id") String user_id,
            @Field("pagename") String pagename
    );


    @FormUrlEncoded
    @POST("/registration/birthdate")
    Call<addfriends_model>  birthdate(
            @Field("user_id") String user_id,
            @Field("birthdate") String birthdate
    );

    @FormUrlEncoded
    @POST("/registration/password")
    Call<addfriends_model>  passsword(
            @Field("user_id") String user_id,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("/signIIn")
    Call<addfriends_model>  signIn(
            @Field("user_id") String user_id,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/editProfile")
    Call<addfriends_model>  editProfile(
            @Field("user_id") String user_id,
            @Field("name") String name,
            @Field("bio") String bio,
            @Field("img") String img,
            @Field("url") String url,
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("/followie")
    Call<addfriends_model>  followie(
            @Field("follower_id") String follower_id,
            @Field("following_id") String following_id
    );

    @FormUrlEncoded
    @POST("/follow")
    Call<addfriends_model>  follow(
            @Field("follower_id") String follower_id,
            @Field("following_id") String following_id
    );

    @FormUrlEncoded
    @POST("/unfollow")
    Call<addfriends_model>  unfollowie(
            @Field("follower_id") String follower_id,
            @Field("following_id") String following_id
    );

    @FormUrlEncoded
    @POST("/Notification/Follower")
    Call<addfriends_model>  notifyFollower(
            @Field("user_id") String user_id
    );

//    @GET("/find/")
//    Call<addfriends_model> searchFriend();

    @FormUrlEncoded
    @POST("/bookmark/videos")
    Call<hashtag_model> bookmarkvideos(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("/bookmark/hashtags")
    Call<hashtag_model> bookmarkHashtags(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("/bookmark/sounds")
    Call<hashtag_model> bookmarkSounds(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("/bookmark/effects")
    Call<hashtag_model> bookmarkEffects(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("/UserVideos")
    Call<profileVideoRow>  userVideos(
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("/UserVideosLock")
    Call<profileVideoRow>  userVideosLock(
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("/UserVideosFavourite")
    Call<profileVideoRow>  userVideosFavourite(
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("/bookmarkHastagVideoss")
    Call<profileVideoRow>  bookmarkHastagVideoss(
            @Field("hashtags") String hashtags
    );


    @FormUrlEncoded
    @POST("/bookmarkSoundVideos")
    Call<profileVideoRow>  bookmarkSoundVideos(
            @Field("songs") String songs
    );

    @FormUrlEncoded
    @POST("/UserVideosView")
    Call<profileVideoRow>  userVideosView(
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("/UserVideosViewFavourite")
    Call<profileVideoRow>  UserVideosViewFavourite(
            @Field("username") String username
    );


    @FormUrlEncoded
    @POST("/follower&followingcount")
    Call<addfriends_model>  followerfollowingcount(
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("/getfollowing")
    Call<hashtag_model>  followingg(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("/getfollower")
    Call<hashtag_model>  followwwer(
            @Field("user_id") String user_id
    );


    @FormUrlEncoded
    @POST("/Notification/Mention")
    Call<video_details> notifyMention(
            @Field("username") String username
    );
    @FormUrlEncoded
    @POST("/PostVideos")
    Call<addfriends_model> postVideos(

            @Field("username") String username,
            @Field("user_id") String user_id,
            @Field("video_url") String video_url,
            @Field("img_url") String img_url,
            @Field("caption") String caption,
            @Field("mention") String mention,
            @Field("video_privacy") String video_privacy,
            @Field("allow_comment") boolean allow_comment,
            @Field("allow_duet") boolean allow_duet,
            @Field("save_to_device") boolean save_to_device


    );

    @FormUrlEncoded
    @POST("/home/videos")
    Call<Home_Model> homeVideos(

            @Field("user_id") String user_id



    );

}
