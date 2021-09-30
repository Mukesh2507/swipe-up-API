package com.example.swipeup_frontend.videos;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.swipeup_frontend.Comment;
import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.addfriends.AddFriends;
import com.example.swipeup_frontend.home.HomeActivity;
import com.example.swipeup_frontend.home.Home_Model_List;
import com.example.swipeup_frontend.profile.User;

import java.util.List;

public class VideosAdapter  extends RecyclerView.Adapter<VideosAdapter.Viewholder> {

    Context context;
    LayoutInflater inflater;
    List<Home_Model_List> videoList;
    public VideosAdapter(Context context,LayoutInflater inflater, List<Home_Model_List> videoList) {
        this.context = context;
        this.inflater=inflater;
        this.videoList = videoList;
    }


    @NonNull
    @Override
    public VideosAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_container_row, parent, false);
        View view= inflater.from(parent.getContext()).inflate(R.layout.video_container_row,parent,false);

        return new VideosAdapter.Viewholder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.Viewholder holder, int position) {
        holder.textVideoTitle.setText(videoList.get(position).getVideoinfo().getUsername());
        holder.caption.setText(videoList.get(position).getVideoinfo().getCaption());
        String xyz = videoList.get(position).getVideoinfo().getImg_url();
        holder.videoView1.setVideoPath(xyz);
        System.out.println("print urlfor media player"+xyz);

//        holder.mmusicname.setText("df");

       // holder.commenting.setText("chamannnanan");

//        MediaController mediaController = new MediaController(this);
//        MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
//
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//                mp.getDuration();
//            }
//        };
////        mp.setOnPreparedListener(preparedListener);
////        mp.prepare();

       holder.videoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
            holder.videoProgressbar.setVisibility(View.GONE);
          //  mediaPlayer.setOnPreparedListener(this);

                mediaPlayer.start();
//                mediaPlayer.getDuration();
//                //mediaPlayer.setOnPreparedListener(this);
            }
        });

        holder.videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
              mediaPlayer.start();
            }
        });


        holder.sharehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//               // sendIntent.putExtra(Intent.EXTRA_TEXT,editText.getText().toString());
//                sendIntent.setType("text/plain");
//                String shareBody ="TEXt";
//                sendIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//
//                Intent.createChooser(sendIntent,"Share via");
//                context.startActivity(sendIntent);


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");

                
               // sendIntent.setType("text/plain");


                Intent shareIntent = Intent.createChooser(sendIntent, null);
//                shareIntent.putExtra(Intent.EXTRA_CHOOSER_TARGETS, myChooserTargetArray);
              context.startActivity(shareIntent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return videoList == null ? 0 :videoList.size();

    }



    public class Viewholder extends RecyclerView.ViewHolder {


        TextView textVideoTitle,caption,mmusicname;
        TextView likessno;
        CardView likes;
         TextView commenting;
        ImageView sharehome;
        VideoView videoView1;
        ProgressBar videoProgressbar;

        public Viewholder(@NonNull View itemView) {

            super(itemView);
             textVideoTitle =itemView.findViewById(R.id.textVideoTitle);
             videoView1 =  itemView.findViewById(R.id.videoview1);
             caption = itemView.findViewById(R.id.textVideoDescription);
             videoProgressbar = itemView.findViewById(R.id.videoProgressBar);
             mmusicname = itemView.findViewById(R.id.musicname);
             sharehome = itemView.findViewById(R.id.share_home);
             commenting = itemView.findViewById(R.id.commenting);
             likes = itemView.findViewById(R.id.likess);
             likessno = itemView.findViewById(R.id.likesssno);


        }
    }
}
