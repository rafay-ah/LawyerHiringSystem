package com.innova.lawyerhiringsystem.fragments;
/* A Fragment for Posting cases
*  This fragment wil  write 'bid/ case' to firebase realtime database
* */

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innova.lawyerhiringsystem.R;


public class PostJobFragment extends Fragment {

    public PostJobFragment() {
        // Required empty public constructor
    }

    public static PostJobFragment newInstance(String param1, String param2) {
        PostJobFragment fragment = new PostJobFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_job, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    }
}