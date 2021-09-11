package com.innova.lawyerhiringsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.innova.lawyerhiringsystem.model.Case;
import com.innova.lawyerhiringsystem.model.Lawyer;
import com.innova.lawyerhiringsystem.model.Quotation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* Lawyer will be able to view case details
*  Place his quotation on selected case*/
public class PlaceBid extends AppCompatActivity {

    TextView tittle, city, budget, statement, courtType, lawyerType;
    Button applyBid;
    String selectedCase;
    public static Case cases;
    private FirebaseAuth mAuth;
    Quotation lawyerQuotation;
    public static Lawyer profile = new Lawyer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_bid);

        tittle = findViewById(R.id.set_case_tittle);
        city = findViewById(R.id.set_case_city);
        budget = findViewById(R.id.set_case_budget);
        statement = findViewById(R.id.set_case_statement);
        courtType = findViewById(R.id.set_case_court);
        lawyerType = findViewById(R.id.set_case_lawyer);
        applyBid= findViewById(R.id.apply_bid);
        getIntentData();
        getCaseData();

        applyBid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                placeQuotation();
            }
        });

    }

    public void getIntentData()
    {
        /*Will get which case was selected by lawyer
        * Case Tittle will obtained*/
        Intent intent = getIntent();
        selectedCase = intent.getStringExtra("caseTittle");
    }

    public void getCaseData()
    {
        /*Search the database
        * for selected case tittle
        * populate teh screen with these details*/

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

                        if (cases.getTittle().equals(selectedCase)){
                            tittle.setText(cases.getTittle());
                            city.setText(cases.getCity());
                            budget.setText(cases.getBudget());
                            statement.setText(cases.getStatement());
                            courtType.setText(cases.getCourttype());
                            lawyerType.setText(cases.getLawyertype());
                            break;
                        }

                    }
                }
                ref.removeEventListener(this);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }

    public void placeQuotation()
    {
        // getting current date
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String date = df.format(c);

        //getting user details

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user= mAuth.getCurrentUser();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users");

        // unattached call
        ref.child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                     profile = task.getResult().getValue(Lawyer.class);

                    // now writing quotation to firebase under 'quotations' node
                    lawyerQuotation = new Quotation(date,profile.getName(),profile.getEmail(),profile.getMobile(),"OPEN",selectedCase,user.getUid());
                    DatabaseReference writeQuotation = database.getReference("quotations");
                    // 'cases' node structure -> cases/userID/caseTittle
                    writeQuotation.child(user.getUid()).child(selectedCase).setValue(lawyerQuotation);

                    Toast.makeText(PlaceBid.this, "Your quotation has been sent to client.",
                            Toast.LENGTH_LONG).show();

                }
            }
        });

//        lawyerQuotation = new Quotation(date,profile.getName(),profile.getEmail(),profile.getMobile(),"OPEN",selectedCase,user.getUid());
//        DatabaseReference writeQuotation = database.getReference("quotations");
//        // 'cases' node structure -> cases/userID/caseTittle
//        writeQuotation.child(user.getUid()).child(selectedCase).setValue(lawyerQuotation);
//
//        Toast.makeText(PlaceBid.this, "Your quotation has been sent to client.",
//                Toast.LENGTH_LONG).show();
    }

}