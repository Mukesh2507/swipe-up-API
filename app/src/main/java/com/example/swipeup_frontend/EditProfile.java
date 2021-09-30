package com.example.swipeup_frontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.options.UpdateOptions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends AppCompatActivity {

    EditText Name, Username, Bio, Website;
    String Appid="swipeup_registration-qzrzs";
    ImageView ticks, backarrow;


    MongoClient mongoClient;
    MongoCollection mongoCollection;
    MongoDatabase mongoDatabase;


    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
    SharedPreferences sharedpreferences;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Name = findViewById(R.id.namee);
        Username = findViewById(R.id.usernamee);
        Bio = findViewById(R.id.bioo);
        Website = findViewById(R.id.websitee);
        ticks = findViewById(R.id.tick);
        backarrow = findViewById(R.id.back);

        // initializing our shared preferences.
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
//        email = sharedpreferences.getString(EMAIL_KEY, null);
        String id = sharedpreferences.getString("id", null);
        System.out.println(id);
        String username = sharedpreferences.getString("username", null);
        System.out.println(username);
        Username.setText(username);
        String name = sharedpreferences.getString("name", null);
        System.out.println(name);
        Name.setText(name);
        String img = sharedpreferences.getString("img_url", null);
        System.out.println(img);

        String website = sharedpreferences.getString("website", null);
        System.out.println(website);
        Website.setText(website);
        String bio = sharedpreferences.getString("bio", null);
        System.out.println(bio);
        Bio.setText(bio);


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(EditProfile.this, ProfileActivity.class);
//                startActivity(i);
            }
        });

//        System.out.println(email);
        ticks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = Name.getText().toString();
                System.out.println(Name.getText().toString());
                String username1 =Username.getText().toString();
                System.out.println(Username.getText().toString());
                String bio1 = Bio.getText().toString();
                System.out.println(Bio.getText().toString());
                String website1 = Website.getText().toString();
                System.out.println(Website.getText().toString());




                Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().editProfile(id, name1, bio1, img, website1, username1);

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
                            if(check.equals("Update")){
                                System.out.println("Otp Send");



                                SharedPreferences.Editor editor = sharedpreferences.edit();
//                                editor.putString("id", check1.get_Id());
                                editor.putString("username", username1);
                                System.out.println(username1);
                                System.out.println(name1);
                                System.out.println(website1);
                                editor.putString("name", name1);
                                editor.putString("bio", bio1);
//                                editor.putString("img_url", check1.getImgurl());
                                editor.putString("website", website1);
                                editor.apply();

//                                Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
////                                intent.putExtra("email", s.toString());
////                                System.out.println(s);
//                                startActivity(intent);



                            }
                            else{
//                                Intent intent=new Intent(getActivity(), OtpVerification_Activity.class);
//                                intent.putExtra("email", s.toString());
//                                System.out.println(s);
//                                startActivity(intent);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
//                                editor.putString("id", check1.get_Id());
                                editor.putString("username", username1);
                                editor.putString("name", name1);
                                System.out.println(username1);
                                System.out.println(name1);
                                System.out.println(website1);
//                                editor.putString("img_url", check1.getImgurl());
                                editor.putString("website", website1);
                                editor.putString("bio", bio1);
                                editor.apply();
                                System.out.println("username exist!");


//                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//                                startActivity(intent);
//                                intent.putExtra("email", s.toString());
//                                System.out.println(s);
//                                startActivity(intent);


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
        });



//        Realm.init(getApplicationContext());
//        App app=new App(new AppConfiguration.Builder(Appid).build());
//        Credentials credentials=Credentials.anonymous();
//        app.loginAsync(credentials,it -> {
//            if (it.isSuccess()) {
//                Log.v("User","as");
//                User user=app.currentUser();
//                mongoClient = user.getMongoClient("mongodb-atlas");
//                mongoDatabase = mongoClient.getDatabase("swipeup");
//                mongoCollection = mongoDatabase.getCollection("registration");
//
//                Document queryFilter = new Document("email", email);
//                mongoCollection.findOne(queryFilter).getAsync(task -> {
//                    if (task.isSuccess()) {
//                        System.out.println(task);
////                        Log.v("data", task.toString());
//                        Document resultdata = (Document) task.get();
//                        System.out.println(resultdata.get("username"));
//                        Username.setText((String) resultdata.get("username"));
//                        Name.setText((String) resultdata.get("name"));
//                        Bio.setText((String) resultdata.get("bio"));
//                        Website.setText((String) resultdata.get("website"));
//                        Log.v("EXAMPLE", "successfully found a document: ");
//                    } else {
//                        Log.e("EXAMPLE", "failed to find document with: ", task.getError());
//                    }
//                });
//
//
//                ticks.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Document queryFilter = new Document("email", email);
//                        Document updateDocument = new Document();
//                        updateDocument.put("name",Name.getText().toString());
//                        updateDocument.put("username", Username.getText().toString());
//                        updateDocument.put("bio",Bio.getText().toString());
//                        updateDocument.put("website",Website.getText().toString());
//                        UpdateOptions updateOptions = new UpdateOptions().upsert(true);
//                        mongoCollection.updateOne(queryFilter, new Document("$set", updateDocument), updateOptions).getAsync(task -> {
//                            if (task.isSuccess()) {
////                        long count = task.get().getModifiedCount();
////                        if (count == 1) {
//                                Log.v("EXAMPLE", "successfully updated a document.");
////                        } else {
////                            Log.v("EXAMPLE", "did not update a document.");
////                        }
//
//
//                            } else {
//                                Log.e("EXAMPLE", "failed to update document with: ", task.getError());
//                            }
//                        });
//                    }
//                });
//
//            }
//            else {
//                Log.e("EXAMPLE","sum failed:");
//            }
//
//        });



//        Realm.init(this);
//        String realmName = "swipeup_registration-qzrzs";
//        RealmConfiguration config = new RealmConfiguration.Builder().name(realmName).build();
//        Realm realm = Realm.getInstance(config);
//        RealmQuery<EditProfile_model> editProfile_modelQuery = realm.where(EditProfile_model.class);


//        realm.executeTransactionAsync(transactionRealm -> { // start a write transaction
//            // get a frog from the database to update
//            EditProfile_model editProfile =
//                    transactionRealm.where(EditProfile_model.class)
//                            .equalTo("name", "Benjamin Franklin").findFirst();
//            // change the frog's name
//            frog.setName("George Washington");
//            // change the frog's species
//            frog.setSpecies("American bullfrog");
//        });


//        long usernamee = editProfile_modelQuery.equalTo("username", "pqrsushant").count();
//        Log.v("EXAMPLE", "Tadpoles: " + usernamee);

    }
}