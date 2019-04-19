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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryPage extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    private static final String TAG = "ViewInformation";
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference mRef;
    String userID;
    private ListView mListView;


    private  FirebaseAuth mAuth;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        mAuth = FirebaseAuth.getInstance();

        tv = findViewById(R.id.tv);

        mListView = findViewById(R.id.HistiryView);

        mRef = mFirebaseDatabase.getReference();


    }

    @Override
    protected void onStart() {
        super.onStart();

            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            authListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null) {
                        startActivity(new Intent(HistoryPage.this, MainActivity.class));
                        finish();
                    }
                    else {
                        userID = user.getUid();
                        mRef.child(userID).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Toast.makeText(getApplicationContext(), "Showing Details...", Toast.LENGTH_SHORT).show();
                                showData(dataSnapshot);
                            }

                            private void showData(DataSnapshot ds) {

                                HitoryGetSet uInfo = new HitoryGetSet();
                                uInfo.setName(ds.child(userID).child("Name").getValue(HitoryGetSet.class).getName());


                                ArrayList<String> array = new ArrayList<>();
                                array.add(uInfo.getName());


                                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,array);
                                mListView.setAdapter(adapter);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                throw databaseError.toException(); // never ignore errors
                            }
                        });
                    }
                }
            };


    }
}