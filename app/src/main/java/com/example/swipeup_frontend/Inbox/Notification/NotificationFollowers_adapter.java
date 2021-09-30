package com.example.swipeup_frontend.Inbox.Notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.profile.User;
import com.example.swipeup_frontend.addfriends.addfriends_model;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFollowers_adapter extends RecyclerView.Adapter<NotificationFollowers_adapter.ViewHolder> {

    Boolean state;
    Context context;
    List<User> userList;

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREFS = "shared_prefs";



    public NotificationFollowers_adapter(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_search_user_row, parent, false);
        return new NotificationFollowers_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Username.setText(userList.get(position).getUserinfo().getUsername());
        holder.Name.setText(userList.get(position).getUserinfo().getName());
        System.out.println(userList.get(position).getUserinfo().getName());
//        holder.checkfollower(userList.get(position).getUserinfo().get_id(), );
//        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//        String userObject = pref.getString("id", null);
//        System.out.println(userObject);
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String agentId = preferences.getString("id", null);
        System.out.println(agentId);
        System.out.println("******id*******");
        holder.checkfollower(userList.get(position).getUserinfo().get_id(), agentId);
        holder.followbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state == true){
                    holder.unfriend(userList.get(position).getUserinfo().get_id(), agentId);
                }else{
                    holder.addfriend(userList.get(position).getUserinfo().get_id(), agentId);
                }
                    System.out.println("click kiya na be!");
                    System.out.println(userList.get(position).getUserinfo().getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 :userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Username;
        TextView Name;
        public CardView followbtn;
        TextView follow_friend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Username = (TextView) itemView.findViewById(R.id.username);
            Name = (TextView) itemView.findViewById(R.id.name);
            followbtn = (CardView) itemView.findViewById(R.id.Username_button);
            follow_friend = (TextView) itemView.findViewById(R.id.tvColorrow);

        }



        private void unfriend(String user_profile_id, String user_id){
            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().unfollowie(user_id, user_profile_id);

            call.enqueue(new Callback<addfriends_model>() {
                @Override
                public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                    System.out.println("sucessfull");
                    System.out.println(response);
                    System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                    if(response.isSuccessful()){

                        String check = response.body().getResponse();
                        System.out.println(check);
                        if(check.equals("unfollowed")){
                            System.out.println("unfollowed");
                            state = false;
                            System.out.println(state);
                            followbtn.setCardBackgroundColor(Color.parseColor("#FF5B71"));
                            follow_friend.setText("Follow");
                            follow_friend.setTextColor(Color.WHITE);
                            System.out.println("Follow");
//                        addfriend(user_profile_id, user_id);
//                        checkfollower(user_profile_id, user_id);
//                        final ColorStateList cardBackgroundColor = followw.getCardBackgroundColor(Color.parseColor("#ffff"));
                        }
                        else{

//                        followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
//                        follow_friend.setText("Friend");
//                        follow_friend.setTextColor(Color.BLACK);
//                        System.out.println("Friend");
//                        state = true;
                            System.out.println("user does not existed!");
//                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
                        }
//                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                    }

                }




                @Override
                public void onFailure(Call<addfriends_model> call, Throwable t) {
                    System.out.println("Error"+call.request().url());
                    if (t instanceof IOException) {
//                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                        System.out.println("this is an actual network failure :( inform the user and possibly retry");
                    }
                    else {
//                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        // todo log to some central bug tracking service
                        System.out.println("conversion issue! big problem :)");
                    }
                }
            });
        }




        private  void addfriend(String user_profile_id, String user_id){
            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().follow(user_id, user_profile_id);

            call.enqueue(new Callback<addfriends_model>() {
                @Override
                public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                    System.out.println("sucessfull");
                    System.out.println(response);
                    System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                    if(response.isSuccessful()){

                        String check = response.body().getResponse();
                        System.out.println(check);
                        if(check.equals("followed")){
                            System.out.println("followed");
                            state = true;
                            System.out.println(state);
                            followbtn.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
                            follow_friend.setText("Friend");
                            follow_friend.setTextColor(Color.BLACK);
                            System.out.println("Friend");
//                        state = true;
                            System.out.println(state);
//                        checkfollower(user_profile_id, user_id);
//                        final ColorStateList cardBackgroundColor = followw.getCardBackgroundColor(Color.parseColor("#ffff"));
                        }
                        else{
//                        followw.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
//                        follow_friend.setText("Friend");
//                        follow_friend.setTextColor(Color.BLACK);
//                        System.out.println("Friend");
//                        state = true;
                            System.out.println("user existed!");
//                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
                        }
//                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                    }

                }




                @Override
                public void onFailure(Call<addfriends_model> call, Throwable t) {
                    System.out.println("Error"+call.request().url());
                    if (t instanceof IOException) {
//                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                        System.out.println("this is an actual network failure :( inform the user and possibly retry");
                    }
                    else {
//                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        // todo log to some central bug tracking service
                        System.out.println("conversion issue! big problem :)");
                    }
                }
            });
        }









        private void checkfollower(String user_profile_id, String user_id) {
//        System.out.println(user_id);

            Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().followie(user_id, user_profile_id);

            call.enqueue(new Callback<addfriends_model>() {
                @Override
                public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {

                    System.out.println("sucessfull");
                    System.out.println(response);
                    System.out.println('*');
//                userList= response.body().getUsers();
//                System.out.println(userList);
//                adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                    if(response.isSuccessful()){

                        String check = response.body().getResponse();
                        System.out.println(check);
                        if(check.equals("Follow")){
                            System.out.println("Follow");
                            state = false;
                            System.out.println(state);
                            followbtn.setCardBackgroundColor(Color.parseColor("#FF5B71"));
                            follow_friend.setText("Follow");
                            follow_friend.setTextColor(Color.WHITE);
                            System.out.println("Friend");
//                        final ColorStateList cardBackgroundColor = followw.getCardBackgroundColor(Color.parseColor("#ffff"));
                        }
                        else if(check.equals("Friend")){
                            followbtn.setCardBackgroundColor(Color.parseColor("#F3F5F6"));
                            follow_friend.setText("Friend");
                            follow_friend.setTextColor(Color.BLACK);
                            System.out.println("Friend");
                            state = true;
                            System.out.println(state);
//                        sendemailotp(s);


//                                        Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                        intent.putExtra("email", abc);
//                                        System.out.println(s);
//                                        startActivity(intent);
                        }
//                            adapters = new addFriendsonclick_adapter(getApplication(),userList);
//                            recyclerView.setAdapter(new addFriendsonclick_adapter(getApplication(), (ArrayList<User>) userList));
                    }

                }




                @Override
                public void onFailure(Call<addfriends_model> call, Throwable t) {
                    System.out.println("Error"+call.request().url());
                    if (t instanceof IOException) {
//                    Toast.makeText(AddFriends.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                        System.out.println("this is an actual network failure :( inform the user and possibly retry");
                    }
                    else {
//                    Toast.makeText(AddFriends.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                        // todo log to some central bug tracking service
                        System.out.println("conversion issue! big problem :)");
                    }
                }
            });

        }










    }




}
