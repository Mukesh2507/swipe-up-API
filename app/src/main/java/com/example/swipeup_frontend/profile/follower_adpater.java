package com.example.swipeup_frontend.profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.user.ViewUser;
import com.example.swipeup_frontend.bookmark.bookmark_hashtag_model;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class follower_adpater extends RecyclerView.Adapter<follower_adpater.Viewholder> {


    Context context;
    List<bookmark_hashtag_model> userList;

    public follower_adpater(Context context, List<bookmark_hashtag_model> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public follower_adpater.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_friends_row, parent, false);
        return new follower_adpater.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull follower_adpater.Viewholder holder, int position) {

//        final bookmark_hashtag_model temp = userList.get(position);

        holder.username.setText(userList.get(position).getFolloweruserinfo().getUsername());
        holder.name.setText(userList.get(position).getFolloweruserinfo().getName());

        holder.cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewUser.class);
                intent.putExtra("_id", userList.get(position).getFolloweruserinfo().get_id());
                intent.putExtra("name", userList.get(position).getFolloweruserinfo().getName());
                intent.putExtra("username", userList.get(position).getFolloweruserinfo().getUsername());
                intent.putExtra("website", userList.get(position).getFolloweruserinfo().getWebsite());
                intent.putExtra("bio", userList.get(position).getFolloweruserinfo().getBio());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
//                context.startActivity(new Intent(context, ProfileActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 :userList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView username, name;
        private LinearLayout cards;
        private CircleImageView imgurl;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            imgurl = itemView.findViewById(R.id.profile_image);
            cards = itemView.findViewById(R.id.card);
        }
    }
}
