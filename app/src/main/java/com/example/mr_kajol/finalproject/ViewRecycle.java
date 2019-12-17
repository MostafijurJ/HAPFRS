package com.example.mr_kajol.finalproject;


import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class ViewRecycle extends AppCompatActivity {

    RecyclerView mrecyclerView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recycle);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Posts List");*/

        mrecyclerView = findViewById(R.id.recyclerView);
        mrecyclerView.setHasFixedSize(true);


        mrecyclerView.setLayoutManager(new LinearLayoutManager(ViewRecycle.this));


        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference("Images");

    }


    //load the data in the recycler view onStrat.


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(

                Model.class,
                R.layout.row,
                ViewHolder.class,
                mRef

        ) {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                viewHolder.setDetails(getApplicationContext(),model.getBengaliName(),model.getUrl(),model.getEnglishName(), model.getFat(), model.getCarbohydrate(), model.getProtein());

            }
        };
         mrecyclerView.setAdapter(firebaseRecyclerAdapter);

    }

}

