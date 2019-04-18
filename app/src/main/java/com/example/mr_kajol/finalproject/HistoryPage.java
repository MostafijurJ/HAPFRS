package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryPage extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    private  FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

       /* FirebaseUser FUser = mAuth.getCurrentUser();
        String userid = FUser.getUid();
        DatabaseReference DR;
        DR = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Height =  dataSnapshot.child("Height").getValue().toString();
                String Weight = dataSnapshot.child("Weight").getValue().toString();
                String Age = dataSnapshot.child("Age").getValue().toString();
                String PAL = dataSnapshot.child("PAL").getValue().toString();

                arrayList.add(Height);
                arrayList.add(Weight);
                arrayList.add(Age);
                arrayList.add(PAL);

               arrayAdapter = new ArrayAdapter(HistoryPage.this, android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);


}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });*/
    }
}