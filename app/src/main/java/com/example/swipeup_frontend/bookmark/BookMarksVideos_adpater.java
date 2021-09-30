package com.example.swipeup_frontend.bookmark;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.videos.viewVideo;

import java.util.List;

public class BookMarksVideos_adpater extends RecyclerView.Adapter<BookMarksVideos_adpater.ViewHolder> {
    List<bookmark_hashtag_model> userList;
    Context context;


    public BookMarksVideos_adpater(Context context, List<bookmark_hashtag_model> userList) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookMarksVideos_adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_first_row_container, parent, false);
        return new BookMarksVideos_adpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarksVideos_adpater.ViewHolder holder, int position) {
        Glide.with(holder.imageView.getContext()).load(userList.get(position).getVideoinfo().getImg_url()).into(holder.imageView);
        holder.textView.setText(userList.get(position).getVideoinfo().getUsername());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, viewVideo.class);
                intent.putExtra("video_username", userList.get(position).getVideoinfo().getUsername());
                intent.putExtra("video_img_url", userList.get(position).getVideoinfo().getImg_url());
//                intent.putExtra("_id", temp.get_Id());
//                System.out.println(temp.get_Id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 :userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.videoimageView);
            this.textView = (TextView) itemView.findViewById(R.id.videoTextView);
            this.constraintLayout = itemView.findViewById(R.id.cardxyz);
        }
    }
}
