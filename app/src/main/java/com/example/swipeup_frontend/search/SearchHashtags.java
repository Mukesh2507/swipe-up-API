package com.example.swipeup_frontend.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swipeup_frontend.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchHashtags#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchHashtags extends Fragment {
private View view;
CardView hs;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchHashtags() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchHashtags.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchHashtags newInstance(int param1, int param2) {
        SearchHashtags fragment = new SearchHashtags();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, String.valueOf(param1));
        args.putString(ARG_PARAM2, String.valueOf(param2));
        fragment.setArguments(args);
        System.out.println(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_search_hashtags, container, false);
        hs=view.findViewById(R.id.hastage_card);
        hs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), HashtagsDisplay.class);
                startActivity(intent);
            }
        });

        return view;
    }


}