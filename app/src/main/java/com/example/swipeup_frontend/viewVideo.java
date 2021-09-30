package com.example.swipeup_frontend;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class viewVideo extends AppCompatActivity {

    VideoView videoView;
    TextView username,textVideoDescription;
    ProgressBar videoProgressbar;
    ImageView sharess;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_container_row);

        videoView = findViewById(R.id.videoview1);
        username = findViewById(R.id.textVideoTitle);
        videoProgressbar = findViewById(R.id.videoProgressBar);
        textVideoDescription= findViewById(R.id.textVideoDescription);
        sharess = (ImageView) findViewById(R.id.share_home);


        String i = getIntent().getStringExtra("video_username");
        String j = getIntent().getStringExtra("video_img_url");
        String k = getIntent().getStringExtra("video_caption");

        sharess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
//                shareIntent.putExtra(Intent.EXTRA_CHOOSER_TARGETS, myChooserTargetArray);
                startActivity(shareIntent);
            }
        });

        System.out.println(i);
        System.out.println(j);
        username.setText(i);
        textVideoDescription.setText(k);
        videoView.setVideoPath(j);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoProgressbar.setVisibility(View.GONE);
                mediaPlayer.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });



//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//                sendIntent.setType("text/plain");
//
//                Intent shareIntent = Intent.createChooser(sendIntent, null);
//                startActivity(shareIntent);
//            }
//        });

    }
}
