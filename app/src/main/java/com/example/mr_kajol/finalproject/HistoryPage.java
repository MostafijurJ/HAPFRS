package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryPage extends AppCompatActivity  {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<HitoryGetSet> arrayList;

    private  FirebaseAuth mAuth;

    myHistory adap;
     RecyclerView recycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        mAuth = FirebaseAuth.getInstance();

        recycle = findViewById(R.id.myrec);
        recycle.setLayoutManager( new LinearLayoutManager(HistoryPage.this));

        if (mAuth.getCurrentUser() == null) {
            //handle the already login user
            Toast.makeText(HistoryPage.this,"No User found",Toast.LENGTH_LONG).show();
           Intent i = new Intent(HistoryPage.this, MainActivity.class);
            startActivity(i);
        }
        else{

            FirebaseUser FUser = mAuth.getCurrentUser();
            String userid = FUser.getUid();

            DatabaseReference reference;

            reference = FirebaseDatabase.getInstance().getReference().child("HistoryTable").child(userid);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    arrayList = new ArrayList<HitoryGetSet>();
                    for(DataSnapshot dsp: dataSnapshot.getChildren())
                    {
                        HitoryGetSet p = dsp.getValue(HitoryGetSet.class);
                        arrayList.add(p);
                    }
                        adap = new myHistory(HistoryPage.this, arrayList);
                        recycle.setAdapter(adap);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }


}