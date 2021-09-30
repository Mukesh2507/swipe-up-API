package com.example.swipeup_frontend.profile;

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
import com.example.swipeup_frontend.videos.videos;
import com.example.swipeup_frontend.videos.viewVideo;

import java.util.List;

public class profileVideoAdapter extends RecyclerView.Adapter<profileVideoAdapter.videoViewHolder>{

    List<videos> userList;
    Context context;

    public profileVideoAdapter(Context context, List<videos> userList) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public videoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.profile_first_row_container, parent, false);
        videoViewHolder viewHolder = new videoViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull videoViewHolder holder, int position) {
//        final profileVideoRow myListData = listdata[position];
//        holder.textView.setText(listdata[position].getView());
//        holder.imageView.setImageResource(listdata[position].getImgId());
//        URL newurl = null;
//        try {
//            newurl = new URL(userList.get(position).getImg_url());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Bitmap mIcon_val = null;
//        try {
//            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holder.imageView.setImageBitmap(mIcon_val);
        Glide.with(holder.imageView.getContext()).load(userList.get(position).getImg_url()).into(holder.imageView);
        holder.textView.setText(userList.get(position).getUsername());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, viewVideo.class);
                intent.putExtra("video_username", userList.get(position).getUsername());
                intent.putExtra("video_img_url", userList.get(position).getImg_url());
                intent.putExtra("video_caption", userList.get(position).getCaption());
//                intent.putExtra("_id", temp.get_Id());
//                System.out.println(temp.get_Id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
//        holder.imageView.setText(userList.get(position).getEffectinfo().getName());
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 :userList.size();
    }

    public class videoViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        ConstraintLayout constraintLayout;
        public videoViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.videoimageView);
            this.textView = (TextView) itemView.findViewById(R.id.videoTextView);
            this.constraintLayout = itemView.findViewById(R.id.cardxyz);
        }
    }
}
