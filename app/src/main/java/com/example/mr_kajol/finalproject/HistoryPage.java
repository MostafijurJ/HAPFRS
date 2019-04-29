package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    private  FirebaseAuth mAuth;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        mAuth = FirebaseAuth.getInstance();

        tv = findViewById(R.id.tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            //handle the already login user
            Toast.makeText(HistoryPage.this,"No User found",Toast.LENGTH_LONG).show();
           Intent i = new Intent(HistoryPage.this, bmiopetation.class);
            startActivity(i);
        }
        else{

            FirebaseUser FUser = mAuth.getCurrentUser();
            String userid = FUser.getUid();

            DatabaseReference DR;
            DR = FirebaseDatabase.getInstance().getReference().child("HistoryTable").child(userid).child("push id");
            DR.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   // Iterable<DataSnapshot> root = dataSnapshot.getChildren();
                  //  Toast.makeText(getApplicationContext(), "ds "+dataSnapshot.getChildren(),Toast.LENGTH_LONG).show();
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                 //       Toast.makeText(getApplicationContext(), "ds "+ds,Toast.LENGTH_LONG).show();

                        for (DataSnapshot d: ds.getChildren()) {

                            String Height = d.getKey() + d.getValue() + "\n".toString();
                            String ch =  d.child("1Height:").getValue(String.class);

                            // tv.append(Height);
                            tv.append(ch);

                            // Log.e("Count" , ""+dataSnapshot.getChildrenCount());
                            //String Height = d.child("1Height").getValue().toString();
                          /*  String Weight = ds.child("Weight").getValue().toString();
                            String Age = ds.child("Age").getValue().toString();
                            String PAL = ds.child("PAL").getValue().toString();
                            String Name = ds.child("Name").getValue().toString();
                            String Email = ds.child("Email").getValue().toString();
                            String Contact_No = ds.child("Phone").getValue().toString();*/
                           /* tv.append(d+": "+ " " +"\n"+ Name + "\n" + "User_Email : " + Email + "\n" + "Height: " + Height + "\n" + "Weight : " +
                                    Weight + "\n" + "Age : " + Age + "\n" + "Physical Activity Level : " + PAL); */
                        }
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }
    }


}