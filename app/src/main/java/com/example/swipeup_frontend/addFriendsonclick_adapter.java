package com.example.swipeup_frontend;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class addFriendsonclick_adapter extends RecyclerView.Adapter<addFriendsonclick_adapter.Viewholder>  {

    Context context;
    List<User> userList;

    public addFriendsonclick_adapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_friends_row, parent, false);
        return new addFriendsonclick_adapter.Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final User temp = userList.get(position);

        holder.username.setText(userList.get(position).getUsername());
        holder.name.setText(userList.get(position).getName());

        holder.cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewUser.class);
                intent.putExtra("name", temp.getName());
                intent.putExtra("username", temp.getUsername());
                intent.putExtra("_id", temp.get_Id());
                intent.putExtra("website", temp.getWebsite());
                intent.putExtra("bio", temp.getBio());
//                System.out.println(temp.get_Id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
//                context.startActivity(new Intent(context, ProfileActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
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
