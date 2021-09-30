package com.example.swipeup_frontend;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideosAdapter  extends RecyclerView.Adapter<VideosAdapter.Viewholder> {

    Context context;
    List<Home_Model_List> videoList;

    public VideosAdapter(Context context, List<Home_Model_List> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public VideosAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_container_row, parent, false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.video_container_row,parent,false);
        return new VideosAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.Viewholder holder, int position) {
        holder.textVideoTitle.setText(videoList.get(position).getVideoinfo().getUsername());
        holder.caption.setText(videoList.get(position).getVideoinfo().getCaption());
        holder.videoView1.setVideoPath(videoList.get(position).getVideoinfo().getImg_url());


       holder.videoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
            holder.videoProgressbar.setVisibility(View.GONE);
            mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.start();
            }
        });

        holder.videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return videoList == null ? 0 :videoList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {


        TextView textVideoTitle,caption;
        VideoView videoView1;
        ProgressBar videoProgressbar;
        public Viewholder(@NonNull View itemView) {
            super(itemView);

             textVideoTitle =itemView.findViewById(R.id.textVideoTitle);
             videoView1 = (VideoView) itemView.findViewById(R.id.videoview1);
             caption = itemView.findViewById(R.id.textVideoDescription);
             videoProgressbar = itemView.findViewById(R.id.videoProgressBar);
        }
    }
}
