package com.innova.lawyerhiringsystem.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innova.lawyerhiringsystem.R;
import com.innova.lawyerhiringsystem.WelcomeScreen;
import com.innova.lawyerhiringsystem.adapter.ViewQuotations;
import com.innova.lawyerhiringsystem.model.Case;
import com.innova.lawyerhiringsystem.model.Quotation;

import java.util.ArrayList;

public class QuotationsFragment extends Fragment {

    EditText query;
    String tittle;
    public static ArrayList<Quotation> quotations;
    Quotation quotation;

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

        Button button = (Button) rootview.findViewById(R.id.search_case_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // event listener for search button
                // logic-> get entered case tittle - search quotation for that case tittle - populate list view with details
                query = rootview.findViewById(R.id.search_case);
                tittle = query.getText().toString();
                quotations = new ArrayList<Quotation>();

                // now query DB for quotation matching entered tittle
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("quotations");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot child : dataSnapshot.getChildren()) {

                            for (DataSnapshot innerChild : child.getChildren())
                            {
                                quotation = innerChild.getValue(Quotation.class);

                                if (quotation.getCaseTittle().equals(tittle)){
                                    // we have found the instance of quotation for case
                                    Log.i("casequote", quotation.getLawyerid());
                                    quotations.add(quotation);

                                }
                            }
                        }
                        // Create the adapter to convert the array to views
                        ViewQuotations adapter = new ViewQuotations(getActivity(), quotations);
                        // Attach the adapter to a ListView
                        ListView listView =  rootview.findViewById(R.id.view_quotations);
                        listView.setAdapter(adapter);
//                        // only populate listview if it is not the first time fragment is created
//                        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
//                                R.layout.available_case_row, caseNames);
//                        listCases.setAdapter(adapter);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });
        return rootview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.

    }
}