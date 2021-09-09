package com.innova.lawyerhiringsystem.fragments;

/*This is fragment of 'Home' option
 * Will show the user current, previous cases and other stats
 * */
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innova.lawyerhiringsystem.R;
import com.innova.lawyerhiringsystem.model.Article;
import com.innova.lawyerhiringsystem.model.Case;

public class HomeFragment extends Fragment {
    String openCases, allCases;
    private FirebaseAuth mAuth;
    TextView previousCases, currentCases;
    Case cases;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);

        previousCases = rootview.findViewById(R.id.content_case_history);
        currentCases = rootview.findViewById(R.id.content_current_case);
        Typeface face = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/segoeui.ttf");
        previousCases.setTypeface(face);
        currentCases.setTypeface(face);
        loadCases();
        return rootview;
    }

    public void loadCases(){
        /* Database read operation
        *  for cases posted*/

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("cases").child(user.getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                openCases = ""; // clearing strings for recurring read -- attached callback
                allCases = "";
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    cases = child.getValue(Case.class);
                    Log.i("cases", cases.getTittle());
                    allCases += "☞  " + cases.getTittle() + "\n";

                    // only if the case status isOpen then it will be displayed as current case
                    if (cases.isIsopen()){
                        openCases += "☞  " +  cases.getTittle() + "\n";
                    }
                }

                previousCases.setText(allCases);
                currentCases.setText(openCases);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
}