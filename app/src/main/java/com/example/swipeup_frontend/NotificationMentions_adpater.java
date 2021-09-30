package com.example.swipeup_frontend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NotificationMentions_adpater extends RecyclerView.Adapter<NotificationMentions_adpater.ViewHolder> {

    Context context;
    List<videos> userList;

    public NotificationMentions_adpater(Context context, List<videos> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public NotificationMentions_adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_mention_row, parent, false);
        return new NotificationMentions_adpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationMentions_adpater.ViewHolder holder, int position) {
        holder.usersnamee.setText(userList.get(position).getUsername());
        Glide.with(holder.videoscreen.getContext()).load(userList.get(position).getImg_url()).into(holder.videoscreen);
        holder.mentionCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, viewVideo.class);
                intent.putExtra("video_username", userList.get(position).getUsername());
                intent.putExtra("video_img_url", userList.get(position).getImg_url());
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
        TextView usersnamee;
        ImageView videoscreen;
        FrameLayout mentionCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usersnamee = itemView.findViewById(R.id.usersnamee);
            videoscreen = itemView.findViewById(R.id.videoscreen);
            mentionCard = itemView.findViewById(R.id.mentionCard);

        }
    }
}
