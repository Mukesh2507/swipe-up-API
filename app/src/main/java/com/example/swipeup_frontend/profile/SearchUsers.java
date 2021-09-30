package com.example.swipeup_frontend.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipeup_frontend.R;
import com.example.swipeup_frontend.api.RetrofitClient;
import com.example.swipeup_frontend.SharedViewModel;
import com.example.swipeup_frontend.addfriends.addfriends_model;
import com.example.swipeup_frontend.search.SearchUser_adapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchUsers#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchUsers extends Fragment {


    private SharedViewModel viewModel;
    private SearchView searchView;
    private searchuserListener listener;
    RecyclerView recyclerView;
    List<User> userList;
    SearchUser_adapter adapters;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    ArrayList<User> userList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchUsers() {

    }

    public interface searchuserListener{
        void onInputAsent(CharSequence input);
    }

    public void updatedEditext(CharSequence newtext){

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchUsers.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchUsers newInstance(String param1, String param2) {
        SearchUsers fragment = new SearchUsers();
        System.out.println(fragment);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            String index1 = getArguments().getString("index", "");
            System.out.println(index1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_users, container, false);



//        getParentFragmentManager().setFragmentResultListener("datafrom1", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
//                String data = result.getString("key");
//                System.out.println(data);
//            }
//        });


//        SearchActivity.setHeartRateListener(new SearchActivity.HeartRateListener() {
//            @Override
//            public void onHeartRate(String value) {
//                System.out.println(value);
//            }
//
//        });



//        Bundle bundle = this.getArguments();
//        String data = bundle.getString("key", " ");
//        System.out.println(data);
//        String data1 = "";
//        if(data != data1){
//            fetchUserss(data);
//            System.out.println("***");
////            getFragmentManager().beginTransaction().detach(this).commit();
//            recyclerView = (RecyclerView) view.findViewById(R.id.User_recyclerview);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            data = data1;
//        }
//        getFragmentManager().beginTransaction().attach(this).commit();

//        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//        fetchUserss(data);
//        System.out.println("***");
//
        recyclerView = view.findViewById(R.id.User_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapters = new SearchUser_adapter(getContext(),userList);
//        recyclerView.setAdapter(adapters);
//        Bundle args = getArguments();
//        String index1 = args.getString("index", "");
//        System.out.println(index1);
        System.out.println("xyzzzzzz");

//        getFragmentManager().beginTransaction().detach(SearchUsers.this).attach(SearchUsers.this).commit();
//        Fragment currentFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.searchviewer);
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.detach(currentFragment);
//        fragmentTransaction.attach(currentFragment);
//        fragmentTransaction.commit();
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
                getParentFragmentManager().setFragmentResultListener("datafrom1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("key");
                System.out.println(data);
                fetchUserss(data);
            }
        });

        recyclerView = getActivity().findViewById(R.id.User_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapters = new SearchUser_adapter(getActivity(),userList);
        recyclerView.setAdapter(adapters);

//        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
//        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<CharSequence>() {
//            @Override
//            public void onChanged(CharSequence charSequence) {
////                recyclerView.setHasFixedSize(true);
////                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
////                adapters = new SearchUser_adapter(getContext(),userList);
////                recyclerView.setAdapter(adapters);
//                System.out.println(charSequence);
//            }
//        });
    }

    public void fetchUserss(String Key){
        System.out.println(Key);
        Call<addfriends_model> call = new RetrofitClient().getInstance().getApi().searchFriend(Key);

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

                    userList= response.body().getUsers();
                    System.out.println(userList);
                    adapters = new SearchUser_adapter(getContext(),userList);
//                    recyclerView.setAdapter(new SearchUser_adapter(getContext(), userList));
                    recyclerView.setAdapter(adapters);
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

//    @Override
//    public void onAttach(Context context){
//        super.onAttach(context);
//        if(context instanceof searchuserListener){
//            listener = (searchuserListener) context;
//        }else{
//            throw new RuntimeException(context.toString());
//        }
//    }
//    @Override
//    public void onDetach(){
//        super.onDetach();
//        listener = null;
//    }
}