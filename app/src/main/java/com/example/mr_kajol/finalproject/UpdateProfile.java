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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;
import com.bumptech.glide.Glide;


public class UpdateProfile extends AppCompatActivity {

    private Button btnChoose, btnUpload;
    private ImageView imageView, img2,img3;

    private Uri filePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        imageView = (ImageView) findViewById(R.id.imgView);
        img2 = (ImageView) findViewById(R.id.imgView2);
        img3 = (ImageView) findViewById(R.id.imgView3);


        String ur = "https://firebasestorage.googleapis.com/v0/b/health-aware-8d0c8.appspot.com/o/person.png?alt=media&token=c7d61691-b1cd-452a-9857-cb0e43dcbe36";
        String url="https://firebasestorage.googleapis.com/v0/b/health-aware-8d0c8.appspot.com/o/Carbo%2Frice-bowl.jpg?alt=media&token=d09cb07c-a888-4475-aa55-1b21d1acd050";
        String url2 = "https://firebasestorage.googleapis.com/v0/b/health-aware-8d0c8.appspot.com/o/Carbo%2Fruti.jpg?alt=media&token=f5d23e7a-67c2-4eb2-8ae3-ab390593b80c";

        Glide.with(getApplicationContext()).load(url2).into(img2);
        Glide.with(getApplicationContext()).load(url).into(imageView);
        Glide.with(getApplicationContext()).load(ur).into(img3);


    }

}


