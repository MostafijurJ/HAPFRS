package com.example.mr_kajol.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;
import com.bumptech.glide.Glide;


public class UpdateProfile extends AppCompatActivity {

    private ImageView imageView11,imageView12,imageView13,imageView21, imageView22, imageView23, imageView31, imageView32, imageView33;
   private TextView TV11, TV12, TV13, TV21, TV22, TV23, TV31, TV32, TV33;
    private CheckBox checkBox11, checkBox12, checkBox13, checkBox21, checkBox22, checkBox23, checkBox31, checkBox32, checkBox33;

    private Uri filePath;



    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        imageView11 = (ImageView) findViewById(R.id.imgView11);
        imageView12 = (ImageView) findViewById(R.id.imgView12);
        imageView13 = (ImageView) findViewById(R.id.imgView13);

        imageView21 = (ImageView) findViewById(R.id.imgView21);
        imageView22 = (ImageView) findViewById(R.id.imgView22);
        imageView23 = (ImageView) findViewById(R.id.imgView23);

        imageView31 = (ImageView) findViewById(R.id.imgView31);
        imageView32 = (ImageView) findViewById(R.id.imgView32);
        imageView33 = (ImageView) findViewById(R.id.imgView33);


        TV11 = findViewById(R.id.tv11);
        TV12 = findViewById(R.id.tv12);
        TV13 = findViewById(R.id.tv13);

        TV21 = findViewById(R.id.tv21);
        TV22 = findViewById(R.id.tv22);
        TV23 = findViewById(R.id.tv23);

        TV31 = findViewById(R.id.tv31);
        TV32 = findViewById(R.id.tv32);
        TV33 = findViewById(R.id.tv33);



        final String UR ="";

        DatabaseReference PR;
        PR = FirebaseDatabase.getInstance().getReference().child("DataSet").child("3");
        PR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               // String FT = dataSnapshot.child("Fat (g)").getValue().toString();
               /* String CD = dataSnapshot.child("Carbohydrate available (g)").getValue().toString();
                   String PT = dataSnapshot.child("Protein (g)").getValue().toString();
               */
               String Nm = dataSnapshot.child("Food name in Bengali").getValue().toString();

                 String tmp = (dataSnapshot.child("url").getValue().toString());

                Glide.with(getApplicationContext()).load(tmp).into(imageView11);

                TV11.setText("Name: "+Nm);
               // TVuri.setText(UR);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        String urll = "https://firebasestorage.googleapis.com/v0/b/health-aware-8d0c8.appspot.com/o/person.png?alt=media&token=c7d61691-b1cd-452a-9857-cb0e43dcbe36";
        String url="https://firebasestorage.googleapis.com/v0/b/health-aware-8d0c8.appspot.com/o/Carbo%2Frice-bowl.jpg?alt=media&token=d09cb07c-a888-4475-aa55-1b21d1acd050";
        String url2 = "https://firebasestorage.googleapis.com/v0/b/health-aware-8d0c8.appspot.com/o/Carbo%2Fruti.jpg?alt=media&token=f5d23e7a-67c2-4eb2-8ae3-ab390593b80c";



        Glide.with(getApplicationContext()).load(url2).into(imageView12);
       Glide.with(getApplicationContext()).load(urll).into(imageView13);


    }

}


