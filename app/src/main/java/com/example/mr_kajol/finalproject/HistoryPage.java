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

public class HistoryPage extends AppCompatActivity implements OnClickListener {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    private  FirebaseAuth mAuth;
    TextView tv,tvhistory;
    Button histo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        mAuth = FirebaseAuth.getInstance();

        tv = findViewById(R.id.tv);
        histo = findViewById(R.id.historybtn);
        tvhistory = findViewById(R.id.tvhistory);

       histo.setOnClickListener(this);


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

            tv.setText(userid);
            DatabaseReference DR;
            DR = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
            DR.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Height =  dataSnapshot.child("Height").getValue().toString();
                    String Weight = dataSnapshot.child("Weight").getValue().toString();
                    String Age = dataSnapshot.child("Age").getValue().toString();
                    String PAL = dataSnapshot.child("PAL").getValue().toString();
                    String Name = dataSnapshot.child("Name").getValue().toString();
                    String Email = dataSnapshot.child("Email").getValue().toString();
                    String Contact_No = dataSnapshot.child("Phone").getValue().toString();

                    tv.setText("User_Name : "+ Name +"\n" + "User_Email : "+ Email +"\n" +"Height: "+ Height +"\n" + "Weight : " +
                            Weight + "\n" + "Age : "+ Age + "\n" + "Physical Activity Level : " +PAL);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.historybtn:{


        FirebaseUser FUser = mAuth.getCurrentUser();
        String userid = FUser.getUid();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("HistoryTable");

       //  HitoryGetSet  hitoryGetSet = new HitoryGetSet();
        String Temp="" ;
        Query phoneQuery = ref.orderByChild("4UserId").equalTo(userid);
        Temp =  phoneQuery.toString();
        tvhistory.setText(Temp);
        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    HitoryGetSet hitoryGetSet = singleSnapshot.getValue(HitoryGetSet.class);
                    tvhistory.setText((CharSequence) hitoryGetSet);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
               // Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });

        break;
            }


        }
    }
}