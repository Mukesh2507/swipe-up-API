package com.example.swipeup_frontend.bookmark;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swipeup_frontend.search.HashtagsDisplay;
import com.example.swipeup_frontend.R;

import java.util.List;

public class BookMarkHashtages_adpater extends RecyclerView.Adapter<BookMarkHashtages_adpater.ViewHolder> {

//    Boolean state;
    Context context;
    List<bookmark_hashtag_model> userList;


    public BookMarkHashtages_adpater(Context context, List<bookmark_hashtag_model> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hashtag_row, parent, false);
        return new BookMarkHashtages_adpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hashtagss.setText(userList.get(position).getHashtaginfo().getName());
        holder.frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HashtagsDisplay.class);
                intent.putExtra("bookmark_hashtag", userList.get(position).getHashtaginfo().getName());
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

        TextView hashtagss;
        FrameLayout frameLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hashtagss = itemView.findViewById(R.id.textView_hashtags);
            frameLayout = itemView.findViewById(R.id.search_hashtags);
        }
    }
}
