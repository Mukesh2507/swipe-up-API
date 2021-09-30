package com.example.swipeup_frontend.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.profile.User;

import java.util.List;

public class SearchUser_adapter extends RecyclerView.Adapter<SearchUser_adapter.Viewholder> {

    Context context;
    List<User> userList;

    public SearchUser_adapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_search_user_row, parent, false);
        return new SearchUser_adapter.Viewholder(view);

//        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.Username.setText(userList.get(position).getUsername());
        holder.Name.setText(userList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 :userList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView Username;
        TextView Name;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            Username = (TextView) itemView.findViewById(R.id.username);
            Name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
