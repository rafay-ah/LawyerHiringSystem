package com.innova.lawyerhiringsystem.fragments;

/* Role Specific to Lawyer Only
* A fragment for viewing all available jobs
* and present option to place bid*/
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innova.lawyerhiringsystem.R;
import com.innova.lawyerhiringsystem.model.Case;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplyJobFragment extends Fragment {
    ListView listCases;
    Case cases; // single object to read from database one by one
    public static ArrayList<Case> openCases; // will contain complete case objects with all attributes
    public static String[] caseNames; // will store only the names of all cases retrieved
    public int caseCounter = 0;

    public ApplyJobFragment() {
        // Required empty public constructor
    }

    public static ApplyJobFragment newInstance(String param1, String param2) {
        ApplyJobFragment fragment = new ApplyJobFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_apply_job, container, false);

        listCases = rootView.findViewById(R.id.available_cases);
        openCases = new ArrayList<Case>();

        // setting custom font for ListView
//        TextView caseRow = rootView.findViewById(R.id.label);
//        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
//                "fonts/segoeui.ttf");
//        caseRow.setTypeface(face);

        // reading and setting available cases on ListView
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("cases");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for (DataSnapshot innerChild : child.getChildren())
                    {
                        cases = innerChild.getValue(Case.class);
                        Log.i("cases", cases.getTittle());

                        // only if the case status isOpen then it will be displayed as current case
                        if (cases.isIsopen()){
                            openCases.add(cases);
//                            caseNames[caseCounter] = (cases.getTittle());
//                            caseCounter++;
                        }
                    }
                }
                // extracting names of all obtained cases
                caseNames = new String[openCases.size()];
                for (int counter = 0; counter < openCases.size(); counter++) {
                    caseNames[counter] = "☞ " + openCases.get(counter).getTittle();
                    caseCounter++;
                }
                // only populate listview if it is not the first time fragment is created
                ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.available_case_row, caseNames);
                listCases.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return rootView;
    }

    public void getAllCases() /*Database read operation to view all cases*/
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("cases");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    for (DataSnapshot innerChild : child.getChildren())
                    {
                        cases = innerChild.getValue(Case.class);
                        Log.i("cases", cases.getTittle());

                        // only if the case status isOpen then it will be displayed as current case
                        if (cases.isIsopen()){
                            openCases.add(cases);
//                            caseNames[caseCounter] = (cases.getTittle());
//                            caseCounter++;
                        }
                    }
                }

                // extracting names of all obtained cases
                caseNames = new String[openCases.size()];
                for (int counter = 0; counter < openCases.size(); counter++) {
                    caseNames[counter] = openCases.get(counter).getTittle();
                    caseCounter++;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}