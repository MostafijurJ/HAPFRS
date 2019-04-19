package com.example.mr_kajol.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.Math.floor;

public class bmiopetation extends AppCompatActivity implements View.OnClickListener {

    Button bmibtn, Historybtn,buttonLogout;
    EditText height,age, pal;
    EditText weight;

    TextView tvheight;
    TextView tvshowbmi;
    TextView tvweight;
    TextView heightTv;
    TextView weightTv;
    TextView tvbmistatus;
    TextView showdata;

    Spinner heightunits;
    Spinner weightunits;
    Spinner genderspiner;

    private Button btnChoose, btnUpload;
    private ImageView imageView;
    private Uri filePath;


    private   final int PICK_IMAGE_REQUEST  = 71;

    private  FirebaseAuth mAuth;

    int heightindex, weightindex,genderindex;
    Double heightincm, weightinkg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiopetation);

        Historybtn = findViewById(R.id.btnhistory);
        bmibtn = findViewById(R.id.btnbmi);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        pal = findViewById(R.id.PAL);
        age = findViewById(R.id.UserAge);

        tvheight = findViewById(R.id.tvheight);
        tvweight = findViewById(R.id.tvWeight);
        tvshowbmi = findViewById(R.id.tvshowbmi);
        tvbmistatus = findViewById(R.id.bmistatus);

        heightunits = findViewById(R.id.heightunits);
        weightunits = findViewById(R.id.weightunits);
        genderspiner = findViewById(R.id.genderspiner);
        showdata = findViewById(R.id.showdata);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imageView = (ImageView) findViewById(R.id.imgView);



        if(mAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

        mAuth = FirebaseAuth.getInstance();
        //getting current user
        FirebaseUser user = mAuth.getCurrentUser();

        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        showdata.setText("Welcome : "+user.getEmail());



        bmibtn.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
        btnChoose.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        Historybtn.setOnClickListener(this);

        // GENDER
        ArrayAdapter<CharSequence> gender = ArrayAdapter.createFromResource(this,R.array.SEX, android.R.layout.simple_spinner_item);
        gender.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderspiner.setAdapter(gender);

        //Height
        ArrayAdapter<CharSequence> heightadepter = ArrayAdapter.createFromResource(this,R.array.heightunits, android.R.layout.simple_spinner_item);
        heightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        heightunits.setAdapter(heightadepter);

        // Weight
        ArrayAdapter<CharSequence> weightadepter = ArrayAdapter.createFromResource(this,R.array.weightunits, android.R.layout.simple_spinner_item);
        weightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        weightunits.setAdapter(weightadepter);

        genderspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Toast.makeText(MainActivity.this," Gender ", Toast.LENGTH_LONG).show();

                genderindex = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        weightunits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                //Toast.makeText(MainActivity.this," Weight", Toast.LENGTH_LONG).show();

                weightindex = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        heightunits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                heightindex = position;
                //Toast.makeText(MainActivity.this,"Height", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });



        // Retriving Data from Server
        FirebaseUser FUser = mAuth.getCurrentUser();
        String userid = FUser.getUid();
        DatabaseReference DR;
        DR = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Height = dataSnapshot.child("Height").getValue().toString();
                String Weight = dataSnapshot.child("Weight").getValue().toString();
                String Age = dataSnapshot.child("Age").getValue().toString();
                String PAL = dataSnapshot.child("PAL").getValue().toString();

                if(Height==null) Height = "00";
                else if(Weight==null)Weight= "00";
                    else if(Age==null) Age ="00";
                    else if(PAL==null) PAL ="00";

                height.setText(Height);
                weight.setText(Weight);
                age.setText(Age);
                pal.setText(PAL);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onClick(View v) {

         String HighttoStore="", WeighttoStore="";

        switch (v.getId()){
            case R.id.btnbmi:{


            Double Height = Double.parseDouble(height.getText().toString());
            Double Weight = Double.parseDouble(weight.getText().toString());
            Double PAL = Double.parseDouble(pal.getText().toString());

            //for height
            if(heightindex == 0){
                heightincm = (Height);
                HighttoStore = heightincm.toString();
            }
            else if(heightindex == 1){
                double temp, fractional,last;
                temp = floor(Height);
               // fractional = (Height - temp)*10;
                // i inch = 2.54 cm  1 foot = 30.48 cm
                heightincm = (temp * 2.54);
                HighttoStore = heightincm.toString();
                //Toast.makeText(MainActivity.this, "fractional"+heightincm,Toast.LENGTH_LONG).show();
            }
            // for weight
            if(weightindex == 0){
                weightinkg = (Weight);
                WeighttoStore = weightinkg.toString();
            }
            else if(weightindex == 1){
                weightinkg = (Weight * 0.453592);
                WeighttoStore = weightinkg.toString();
            }

                String passHeight =  HighttoStore.trim();
                String passWeight =  WeighttoStore.trim();
                String Age = age.getText().toString().trim();
                String pal = PAL.toString().trim();

            FirebaseUser user = mAuth.getCurrentUser();
            FirebaseDatabase data = FirebaseDatabase.getInstance();
            DatabaseReference MyRefff = data.getReference("Users");

              UpdateClass updateClass =new UpdateClass(

                        passHeight,
                        passWeight,
                        Age,
                      pal
                );
              Map<String,Object> map = updateClass.toMap();

                String userid = user.getUid();
              MyRefff.child(userid).updateChildren(map);



                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference HistoryReff = database.getReference("HistoryTable");

                //get date and Time
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                String date = formattedDate.toString();
                String time = c.toString();

                Map<String, String> HistoryMap = new HashMap<String, String>();
                // For History Checking  Height Weight
                HistoryMap.put("1Height : ", passHeight);
                HistoryMap.put("2Weight : ", passWeight);
                HistoryMap.put("4UserId : ", userid);
                HistoryMap.put("3Time : ", time);
                HistoryMap.put("5PAL : ", pal);
                HistoryReff.child(userid).push().setValue(HistoryMap);


                double bmi, temp, check=1;

            temp = heightincm / 100;
            check = temp * temp;
            bmi = (weightinkg / check);
            String dc = new DecimalFormat("##.##").format(bmi);
            tvshowbmi.setText("Your BMI is : " + dc);

           /* ///bmi status
            String Status = "Current Status: ";
            String Status1 = "Very severely underweight.";
            String Status2 = "Severely underweight";
            String Status3 = "Underweight";
            String Status4 = "Normal (healthy weight)";
            String Status5 = "Overweight";

                if(StatusCheck(bmi)==-1){
                    tvbmistatus.setText(Status+Status1);
                }
                if(StatusCheck(bmi)==1){
                    tvbmistatus.setText(Status+Status2);
                }
                if(StatusCheck(bmi)==2){
                    tvbmistatus.setText(Status+Status3);
                }
                if(StatusCheck(bmi)==3){
                    tvbmistatus.setText(Status+Status4);
                }
                if(StatusCheck(bmi)==4){
                    tvbmistatus.setText(Status+Status5);
                }
                if(StatusCheck(bmi)==55){
                    tvbmistatus.setText(Status+"Strongly OverWeight");
                }
            */

            break;
        }

            case R.id.buttonLogout:{
                mAuth.signOut();
                //closing activity
                finish();
                //starting login activity
               Intent i = new Intent(bmiopetation.this, MainActivity.class);
               startActivity(i);
               break;
            }

            case R.id.btnChoose:{
                chooseImage();
                break;
            }
            case R.id.btnUpload:{
               // uploadImage();
               break;
            }
            case R.id.btnhistory:{
               //redirect to histiry Page

                Intent i = new Intent(bmiopetation.this,HistoryPage.class);
                startActivity(i);
               break;
            }

        }

    }



    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }



    private void uploadImage() {

        FirebaseStorage storage;
        StorageReference storageReference;


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReferenceFromUrl("gs://health-aware-8d0c8.appspot.com/");

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            //"images/"+
            StorageReference ref = storageReference.child(UUID.randomUUID().toString());
           // StorageReference childRef = ref.child("image.jpg");
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(bmiopetation.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(bmiopetation.this, "Failed.."+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }



    public  double StatusCheck(Double Bmi){

        if(Bmi < 15)
            return  -1;
        else if(Bmi > 15 && Bmi < 16)
            return 1;
        else if(Bmi >= 16 && Bmi < 18.5)
            return 2;
        else if(Bmi > 18.5 && Bmi < 25)
            return 3;
        else if(Bmi > 25 && Bmi < 30)
            return 4;
        else if(Bmi>30)return 55;

        return 6;
    }

}
