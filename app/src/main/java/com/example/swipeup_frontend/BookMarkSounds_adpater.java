package com.example.swipeup_frontend;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookMarkSounds_adpater extends RecyclerView.Adapter<BookMarkSounds_adpater.ViewHolder> {

    Context context;
    List<bookmark_hashtag_model> userList;

    public BookMarkSounds_adpater(Context context, List<bookmark_hashtag_model> userList) {
        this.context = context;
        this.userList = userList;
    }


    @NonNull
    @Override
    public BookMarkSounds_adpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sound_row, parent, false);
        return new BookMarkSounds_adpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookMarkSounds_adpater.ViewHolder holder, int position) {
        holder.soundname.setText(userList.get(position).getSoundinfo().getName());
        holder.searchSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SongsDisplay.class);
                intent.putExtra("bookmark_sound", userList.get(position).getSoundinfo().getName());
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

        TextView soundname;
        FrameLayout searchSound;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soundname= itemView.findViewById(R.id.textView_sound);
            searchSound = itemView.findViewById(R.id.search_sound);
        }
    }
}
