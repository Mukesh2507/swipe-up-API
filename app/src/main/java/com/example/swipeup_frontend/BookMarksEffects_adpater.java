package com.example.swipeup_frontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookMarksEffects_adpater extends RecyclerView.Adapter<BookMarksEffects_adpater.ViewHolder>  {

    Context context;
    List<bookmark_hashtag_model> userList;
    public BookMarksEffects_adpater(Context context, List<bookmark_hashtag_model> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.effects_row, parent, false);
        return new BookMarksEffects_adpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.effectname.setText(userList.get(position).getEffectinfo().getName());
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 :userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView effectname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            effectname = itemView.findViewById(R.id.effect_name);
        }
    }
}
