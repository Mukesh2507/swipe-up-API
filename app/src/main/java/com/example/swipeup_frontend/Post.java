package com.example.swipeup_frontend;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

//import com.amplifyframework.AmplifyException;
//import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
//import com.amplifyframework.core.Amplify;
//import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.storage.operation.StorageGetUrlOperation;
import com.amplifyframework.storage.result.StorageUploadFileResult;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

//
//import com.amplifyframework.datastore.generated.model.AmplifyModelProvider;
//
//

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Post extends Activity implements View.OnClickListener {


    private static int RESULT_LOAD_IMAGE = 1;
    private String photoPath;

     private URL abcd;
    private Uri fileUri;
    private Bitmap bitmap;

    private static final int CHOOSING_IMAGE_REQUEST = 1234;
     SwitchCompat btn1,btn2,btn3;
    VideoView videoView;
    CardView upload;
    Uri videouri;
    TextView email2,userEmailfi,postnow;
    EditText vTitle;
    String video_url,img_url,user_id,caption,mention,video_privacy;
    boolean allow_comment,allow_duet,save_to_device;
    public static final String SHARED_PREFS = "shared_prefs";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        videoView=(VideoView)findViewById(R.id.videoView);
        vTitle=findViewById(R.id.vTitle);

        email2=findViewById(R.id.email12_0);
        postnow=findViewById(R.id.postnow);
        userEmailfi=findViewById(R.id.userEmailfi);

        upload=findViewById(R.id.upload);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.btn1:
                        Log.i("switch_compat", isChecked + "");
                        break;

                }
            }
        });
        btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.btn2:
                        Log.i("switch_compat", isChecked + "");
                        break;

                }
            }
        });
        btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.btn3:
                        Log.i("switch_compat", isChecked + "");
                        break;

                }
            }
        });

//        btn2.setOnCheckedChangeListener(this);
//        btn3.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) this);

        upload.setOnClickListener(this);
//        videoView.setOnClickListener(this);

upload.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
//        video_url = "jdd";
//
//         img_url ="xyz";

        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::");
         sharedpreferences = this.getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
         String userId = sharedpreferences.getString("id", null);
        System.out.println("print user id here ..............." +userId);
        String username = sharedpreferences.getString("username",null);
        System.out.println(username);

//        System.out.println("printing caption herrrrrrreeeeeeeeeeeeeeeeee");
//         caption = vTitle.getText().toString();

//        System.out.println(caption);
//         mention="@mukesh";
//         video_privacy ="xsxx";
//         allow_comment = false;
//         allow_duet = false;
//         save_to_device = false;

         upload.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 video_url = "jdd";
                 System.out.println(video_url);

                 img_url ="xyz";
                 System.out.println(img_url);

                 user_id = userId;
                 System.out.println("print user id here buddy " + user_id);
                 System.out.println("printing caption herrrrrrreeeeeeeeeeeeeeeeee");
                 caption = vTitle.getText().toString();
                 System.out.println(caption);

                 mention="@mukesh";
                 System.out.println(mention);
                 video_privacy ="xsxx";
                 System.out.println(video_privacy);
                 allow_comment = false;
                 allow_duet = false;
                 save_to_device = false;
             }
         });

       // ArrayList<String> ab = new ArrayList<>();

//        try {
//            Amplify.configure(getApplicationContext());
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }

//        getApplicationContext().startService(new Intent(getApplicationContext(), TransferService.class));

        // Initialize the AWSMobileClient if not initialized
//        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
//            @Override
//            public void onResult(UserStateDetails userStateDetails) {
////                Log.i(TAG,"AWSMobileClient initialized. User State is " + userStateDetails.getUserState());
//                System.out.println("AWSMobileClient initialized. User State is " + userStateDetails.getUserState());
////                uploadWithTransferUtility();
//            }
//
//            @Override
//            public void onError(Exception e) {
////                Log.e(TAG, "Initialization error.", e);
////                System.out.println("Initialization error.", e);
//            }
//        });




        try {
            // Add these lines to add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());


            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "sushant234.sarvade@gmail.com")
//                .build();
//        Amplify.Auth.signUp("sushant234", "sushant11", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );


        Amplify.Auth.signIn(
                "sushant234",
                "sushant11",
                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                error -> Log.e("AuthQuickstart", error.toString())
        );




//        Amplify.Auth.confirmSignUp(
//                "sushant234",
//                "673238",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );

//        Amplify.Auth.signIn(
//                "sushant234",
//                "Sarvade1@",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );

//        Amplify.Auth.fetchUserAttributes(
//                attributes -> Log.i("AuthDemo", "User attributes = " + attributes.toString()),
//                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
//        );


//        Post1 post = Post1.builder()
//                .title("Create an Amplify DataStore app")
//                .build();
//
//        Amplify.DataStore.save(post,
//                result -> Log.i("MyAmplifyApp", "Created a new post successfully"),
//                error -> Log.e("MyAmplifyApp",  "Error creating post", error)
//        );

//            uploadFile();

//        Todo todo = Todo.builder()
//                .name("My first todo")
//                .description("todo description")
//                .build();
//
//        Amplify.API.mutate(
//                ModelMutation.create(todo),
//                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                error -> Log.e("MyAmplifyApp", "Create failed", error)
//        );


        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent=new Intent();
                                intent.setType("video/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(intent,101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

         postnow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 video_url =abcd.toString();
                 System.out.println(video_url);
                   String Username = username;
                 System.out.println(username);
                 img_url ="xyz";
                 System.out.println(img_url);

                 user_id = userId;
                 System.out.println("print user id here buddy " + user_id);
                 System.out.println("printing caption herrrrrrreeeeeeeeeeeeeeeeee");
                 caption = vTitle.getText().toString();
                 System.out.println(caption);

                 mention="@mukesh";
                 System.out.println(mention);
                 video_privacy ="xsxx";
                 System.out.println(video_privacy);
                 allow_comment = false;
                 allow_duet = false;
                 save_to_device = false;

                  Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().postVideos(Username,user_id,video_url
                  ,img_url,caption,mention,video_privacy,allow_comment,allow_duet,save_to_device);
                  call.enqueue(new Callback<addfriends_model>() {
                      @Override
                      public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {
                          System.out.println("sucessfull");
                          System.out.println(response);
                      }

                      @Override
                      public void onFailure(Call<addfriends_model> call, Throwable t) {

                      }
                  });

             }
         });
//         upload.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                 Call<addfriends_model> calls = new RetrofitClient().getInstance().getApi().postVideos(user_id,video_url,img_url,caption,mention,video_privacy,allow_comment,allow_duet,save_to_device);
//                 calls.enqueue(new Callback<addfriends_model>() {
//                     @Override
//                     public void onResponse(Call<addfriends_model> call, Response<addfriends_model> response) {
//                         user_id=response.body().getResponse();
//                         System.out.println("user_id printing here please check it" +user_id);
//                         video_url=response.body().getResponse();
//                         System.out.println("video_url printing here" +video_url);
//                         img_url=response.body().getResponse();
//                         caption=response.body().getResponse();
//                         System.out.println("print caption here ../....."+caption);
//                         mention=response.body().getResponse();
//                         video_privacy=response.body().getResponse();
//                         allow_comment=response.isSuccessful();
//                         allow_duet=response.isSuccessful();
//                         save_to_device=response.isSuccessful();
//
//
//                     }
//
//                     @Override
//                     public void onFailure(Call<addfriends_model> call, Throwable t) {
//
//                         Toast.makeText(Post.this, "some problems with api please check it", Toast.LENGTH_SHORT).show();
//
//                     }
//                 });
//
//
//             }
//         });

//        try {
//            // Add these lines to add the AWSCognitoAuthPlugin and AWSS3StoragePlugin plugins
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

//        if (i == R.id.upload) {
//            uploadFile(image);
//        }
//        else if (i == R.id.videoView) {
//            choosePhoto();
//        }
//        else if (i == R.id.btn_download) {
//            downloadFile();
//        }
    }

    static String getAlphaNumericString(int n)
    {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }


    private void uploadFile(String path) {
//        File f3=new File(Environment.getExternalStorageDirectory()+"/inpaint/");
//        if(!f3.exists())
//            f3.mkdirs();
//        OutputStream outStream = null;
//        String x = getAlphaNumericString(20);
//        String xyz = "test/"+x+".jpeg";
//        File file = new File(Environment.getExternalStorageDirectory() + "/inpaint/"+xyz);
//        try {
//            outStream = new FileOutputStream(file);
//            image.compress(Bitmap.CompressFormat.JPEG, 85, outStream);
//            outStream.close();
//            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(file.getAbsoluteFile());
//        System.out.println();
        int n = 20;
//        InputStream exampleInputStream = null;
//        try {
//            exampleInputStream = getContentResolver().openInputStream(videouri);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        System.out.println(getAlphaNumericString(n));
        String x = getAlphaNumericString(n);
        String xyz = "test/"+x+".mp4";
//        System.out.println(xyz);
//        Amplify.Storage.uploadInputStream(
//                xyz,
//                exampleInputStream,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );


//        Amplify.Storage.uploadFile(
//                xyz,
//                file.getAbsolutePath(),
//                new ResultListener<StorageUploadFileResult>() {
//                    @Override
//                    public void onResult(StorageUploadFileResult result) {
//                        Log.i("StorageQuickStart", "Successfully uploaded: " + result.getKey());
//                    }
//
//                    @Override
//                    public void onError(Throwable error) {
//                        Log.e("StorageQuickstart", "Upload error.", error);
//                    }
//                }
//        );   Arr

        Amplify.Storage.uploadFile(
                xyz,
                new File(path),
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );

           //String[] ab = new String[];

        StorageGetUrlOperation<?> a = Amplify.Storage.getUrl(
                xyz,
               result -> abcd=result.getUrl(),

                error -> Log.e("MyAmplifyApp", "URL generation failure", error)

        );
        System.out.println("6545678906");
        System.out.println(a);
        System.out.println("rsponse url");
        System.out.println(abcd);
      ;
    }












//    public void uploadWithTransferUtility() {
//
//        TransferUtility transferUtility =
//                TransferUtility.builder()
//                        .context(getApplicationContext())
//                        .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
//                        .s3Client(new AmazonS3Client(AWSMobileClient.getInstance()))
//                        .build();
//
//        File file = new File(getApplicationContext().getFilesDir(), "sample.mp4");
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//            writer.append("Howdy World!");
//            writer.close();
//        }
//        catch(Exception e) {
////            Log.e(TAG, e.getMessage());
//            System.out.println(e.getMessage());
//        }
//
//        TransferObserver uploadObserver =
//                transferUtility.upload(
//                        "public/sample.mp4",
//                        new File(String.valueOf(videouri),"sample.mp4"));
//
//        // Attach a listener to the observer to get state update and progress notifications
//        uploadObserver.setTransferListener(new TransferListener() {
//
//            @Override
//            public void onStateChanged(int id, TransferState state) {
//                if (TransferState.COMPLETED == state) {
//                    // Handle a completed upload.
//                }
//            }
//
//            @Override
//            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
//                int percentDone = (int)percentDonef;
//
////                Log.d(TAG, "ID:" + id + " bytesCurrent: " + bytesCurrent
////                        + " bytesTotal: " + bytesTotal + " " + percentDone + "%");
//                System.out.println("ID:" + id + " bytesCurrent: " + bytesCurrent
//                        + " bytesTotal: " + bytesTotal + " " + percentDone + "%");
//            }
//
//            @Override
//            public void onError(int id, Exception ex) {
//                // Handle errors
//            }
//
//        });
//
//        // If you prefer to poll for the data, instead of attaching a
//        // listener, check for the state and progress in the observer.
//        if (TransferState.COMPLETED == uploadObserver.getState()) {
//            // Handle a completed upload.
//        }
//
////        Log.d(TAG, "Bytes Transferred: " + uploadObserver.getBytesTransferred());
//        System.out.println("Bytes Transferred: " + uploadObserver.getBytesTransferred());
//        System.out.println("Bytes Total: " + uploadObserver.getBytesTotal());
////        Log.d(TAG, "Bytes Total: " + uploadObserver.getBytesTotal());
//    }


























//    private void ChosseFile() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Image"), CHOOSING_IMAGE_REQUEST);
//    }

//    private void uploadFile() {
//
//    }


//    public void choosePhoto() {
//        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(i, RESULT_LOAD_IMAGE);
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//            // String picturePath contains the path of selected Image
//            photoPath = picturePath;
//            System.out.println(photoPath);
//        }
//    }

//    private void uploadFile() {
//        System.out.println(getApplicationContext().getFilesDir());
//        System.out.println("&&&&");
//        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey123");
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append("Example file contents");
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//        }
//
//        Amplify.Storage.uploadFile(
//                "ExampleKey123",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }

//    private void uploadFile() {
//        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey5");
////        exampleFile.getAbsolutePath(videouri);
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append("Example file contents");
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//        }
//
//        Amplify.Storage.uploadFile(
//                "ExampleKey5",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }

//    private void uploadInputStream() {
//        InputStream exampleInputStream = null;
//        try {
//            exampleInputStream = getContentResolver().openInputStream(videouri);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Amplify.Storage.uploadInputStream(
//                "ExampleKey",
//                exampleInputStream,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }



//@Override
//protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    if(requestCode==101 && resultCode==RESULT_OK)
//    {
////        videouri=data.getData();
////        videoView.setVideoURI(videouri);
//        InputStream is = null;
//        try {
//            is = getContentResolver().openInputStream(data.getData());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Bitmap image = BitmapFactory.decodeStream(is);
//        System.out.println("abc");
//
//        uploadFile(image);
////        final String path = getPathFromURI(videouri);
////        if (path != null) {
////            System.out.println(path);
////            uploadFile(path);
//////            File f = new File(path);
//////            videouri = Uri.fromFile(f);
////        }
//    }
//
//}



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String path = getRealPathFromURI(getApplicationContext(), uri);

            Log.e("Check", "URI Path : " + uri.getPath());
            Log.e("Check", "Real Path : " + path);
            uploadFile(path);
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,null
                , MediaStore.Video.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }




//private void uploadFile() {
//
//    String[] proj = {MediaStore.Video.Media.DATA};
//    System.out.println(proj);
//    Cursor cursor = getApplicationContext().getContentResolver().query(videouri, proj, null, null, null);
//    System.out.println(cursor);
//    cursor.moveToFirst();
//    int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
//    System.out.println(column_index);
//    String res = cursor.getString(column_index);
//
//    cursor.close();
//
//    System.out.println(res);
//    System.out.println("%%%%");
//
//
//    final String path = getPathFromURI(videouri);
//    if (path != null) {
//        File f = new File(path);
////        selectedImageUri = Uri.fromFile(f);
//        System.out.println(f);
//    }else {
//        System.out.println("path is null");
//    }
//
//        System.out.println(getApplicationContext().getFilesDir());
//        System.out.println("&&&&");
//        String a = videouri.getPath();
//        System.out.println(a);
//        File exampleFile = new File(getApplicationContext().getFilesDir(), a);
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append("Example file contents");
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//        }
//
//        Amplify.Storage.uploadFile(
//                "Mukesh",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }
//    private void uploadFile(String path) {
//        System.out.println(path);
//        System.out.println("^^^");
//    }

    public String getPathFromURI(Uri contentUri) {
        String res = "";
        System.out.println(contentUri);
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }




    //    private String getPath(Uri videouri) {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(videouri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA); // Line of error
//        cursor.moveToFirst();
//
//        return cursor.getString(column_index);
//    }


//    public String getExtension()
//    {
//        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
//        return  mimeTypeMap.getExtensionFromMimeType(getContentResolver().getType(videouri));
//    }


//    private void uploadFile() {
//        System.out.println(getApplicationContext().getFilesDir());
//        System.out.println("##");
//        System.out.println(videouri);
//        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append("Example file contents");
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//            System.out.println("***");
//        }
//
//        Amplify.Storage.uploadFile(
//                "ExampleKey",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }






//    private void uploadFile() {
//        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append("Example file contents");
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//        }
//
//        Amplify.Storage.uploadFile(
//                "ExampleKey",
//                exampleFile,
//                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//        );
//    }
}
