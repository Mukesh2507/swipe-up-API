package com.example.swipeup_frontend;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.OrderedRealmCollection;
import io.realm.RealmModel;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.mongo.iterable.MongoCursor;
import retrofit2.Callback;

public class addfriend_adapter extends RecyclerView.Adapter<addfriend_adapter.Viewholder> implements Filterable {

    Context context;
    private ArrayList<User> userList;
    private ArrayList<User> backup;

    public addfriend_adapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
        this.backup = new ArrayList<>(userList);
    }

    @NonNull
    @Override
    public addfriend_adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_friends_row, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull addfriend_adapter.Viewholder holder, int position) {

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

//    @Override
//    public Filter getFilter() {
//        return filter;
//    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {

            System.out.println(keyword);
            ArrayList<User> filtereddata = new ArrayList<>();
            if(keyword.toString().isEmpty()){
                System.out.println("empty");
            }
//                filtereddata.addAll(backup);
            else{
                for(User user: backup){
                    if(user.getUsername().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(user);
                        System.out.println(user);
                }
            }
//            return null;
            FilterResults results = new FilterResults();
            results.values = filtereddata;
            System.out.println(filtereddata);
            System.out.println("**");
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

//            userList.clear();
            System.out.println(results.values);
            userList.addAll((ArrayList<User>)results.values);
            notifyDataSetChanged();

        }
    };

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
