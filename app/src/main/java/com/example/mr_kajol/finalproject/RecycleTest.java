package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.mr_kajol.finalproject.bmiopetation.MY_PREFS_NAME;

public class RecycleTest extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Profile> list;
    MyAdapter adapter;

    TextView tvRequiredCal,tvSelectedCAL;
    public  static String RequiredCalorie="";
    public Double CalSend=0.0;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_test);


        tvRequiredCal = findViewById(R.id.tv_required_CAL);
        tvSelectedCAL = findViewById(R.id.tv_your_Selected_CaL);

        //Get the bundle
         Bundle bundle = getIntent().getExtras();
         RequiredCalorie = bundle.getString("NetCAL");



        tvRequiredCal.setText("Your Required Calorie: "+RequiredCalorie+" KAL");


        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));


        reference = FirebaseDatabase.getInstance().getReference().child("Images");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Profile>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Profile p = dataSnapshot1.getValue(Profile.class);
                    list.add(p);
                }
                adapter = new MyAdapter(RecycleTest.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RecycleTest.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static void setRequireCalorie(String cal){
        RequiredCalorie=cal;
    }
    public static  String getRequiredCalorie() {
        return RequiredCalorie;
    }


}
