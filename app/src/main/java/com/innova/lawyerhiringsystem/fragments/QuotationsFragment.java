package com.innova.lawyerhiringsystem.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.innova.lawyerhiringsystem.R;

public class QuotationsFragment extends Fragment {
    public QuotationsFragment() {
        // Required empty public constructor
    }

    public static QuotationsFragment newInstance(String param1, String param2) {
        QuotationsFragment fragment = new QuotationsFragment();
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
        View rootview =  inflater.inflate(R.layout.fragment_quotations, container, false);

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.quotations_row);

        ListView listView = (ListView) rootview.findViewById(R.id.view_quotations);
        listView.setAdapter(adapter);
        return rootview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    }
}