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
import android.util.Log;
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
    EditText height;
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
    Spinner palspiner;
    private  String Height="", Weight="";

    private   final int PICK_IMAGE_REQUEST  = 1;

    private  FirebaseAuth mAuth;

    int heightindex, weightindex,palindex;
    Double heightincm, weightinkg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiopetation);

        Historybtn = findViewById(R.id.btnhistory);
        bmibtn = findViewById(R.id.btnbmi);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

        tvheight = findViewById(R.id.tvheight);
        tvweight = findViewById(R.id.tvWeight);
        tvshowbmi = findViewById(R.id.tvshowbmi);
        tvbmistatus = findViewById(R.id.bmistatus);

        heightunits = findViewById(R.id.heightunits);
        weightunits = findViewById(R.id.weightunits);
        palspiner = findViewById(R.id.palspiner);
        showdata = findViewById(R.id.showdata);


        mAuth = FirebaseAuth.getInstance();




        if(mAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

        //getting current user
        FirebaseUser user = mAuth.getCurrentUser();

        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        //showdata.setText("Welcome : "+user.getEmail());


        Historybtn.setOnClickListener(this);
        bmibtn.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);

        //Height
        ArrayAdapter<CharSequence> heightadepter = ArrayAdapter.createFromResource(this,R.array.heightunits, android.R.layout.simple_spinner_item);
        heightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        heightunits.setAdapter(heightadepter);

        // Weight
        ArrayAdapter<CharSequence> weightadepter = ArrayAdapter.createFromResource(this,R.array.weightunits, android.R.layout.simple_spinner_item);
        weightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        weightunits.setAdapter(weightadepter);

        // Pal Spiner
        ArrayAdapter<CharSequence> paladepter = ArrayAdapter.createFromResource(this,R.array.PalLevel, android.R.layout.simple_spinner_item);
        weightadepter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        palspiner.setAdapter(paladepter);


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
                // your code here
            }
        });

        palspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                palindex = position;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
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
                 Height = dataSnapshot.child("Height").getValue().toString();
                 Weight = dataSnapshot.child("Weight").getValue().toString();
               // String PAL = dataSnapshot.child("PAL").getValue().toString();

                if(Height==null) Height = "00";
                else if(Weight==null)Weight= "00";
                    //else if(PAL==null) PAL ="00";

                height.setText(Height);
                weight.setText(Weight);
                //pal.setText(PAL);

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
                String tempheight = new DecimalFormat("##.##").format(heightincm).trim();
                HighttoStore = tempheight.toString();
            }
            // for weight
            if(weightindex == 0){
                weightinkg = (Weight);
                WeighttoStore = weightinkg.toString();
            }
            else if(weightindex == 1){
                weightinkg = (Weight * 0.453592);
                String tempWeight = new DecimalFormat("##.##").format(weightinkg).trim();
                WeighttoStore = tempWeight.toString();

            }

            //defining physical Activity level
                double palscore=0;

                if(palindex == 0){
                    palscore = 1.4;
                }
                else if(palindex == 1){
                  palscore = 1.55;
                } else if(palindex == 2){
                  palscore = 1.85;
                }else if(palindex == 3){
                  palscore = 2.20;
                }
                else if(palindex == 4){
                  palscore = 2.4;
                }

                String pal = new DecimalFormat("##.##").format(palscore).trim();

                String passHeight =  HighttoStore;
                String passWeight =  WeighttoStore;

                FirebaseUser user = mAuth.getCurrentUser();
                String userid = user.getUid();


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference HistoryReff = database.getReference("HistoryTable");

                //get date and Time
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c);

                String date = formattedDate.toString();
                String time = c.toString();

                // Retriving Data from Server
                FirebaseUser FUser = mAuth.getCurrentUser();
                String Uuserid = FUser.getUid();
                DatabaseReference DR;

                String RHeight="", RWeight="";
                DR = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                DR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String RHeight = dataSnapshot.child("Height").getValue().toString();
                        String RWeight = dataSnapshot.child("Weight").getValue().toString();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Log.d("PassHeight" , ""+passHeight);
                Log.d("PassHeight" , ""+passWeight);

                if((passHeight.equals(Height) ) || (passWeight.equals(Weight))) {
                    Toast.makeText(getApplicationContext()," Data are same", Toast.LENGTH_LONG).show();
                }else{
                    Map<String, String> HistoryMap = new HashMap<String, String>();
                    // For History Checking  Height Weight
                    HistoryMap.put("Height : ", passHeight+" cm");
                    HistoryMap.put("Weight : ", passWeight+ " Kg");
                    HistoryMap.put("Date : ", date);
                    HistoryMap.put("PAL : ", pal);
                    HistoryReff.child(userid).push().setValue(HistoryMap);
                }

                // Updating User table's value
                FirebaseDatabase data = FirebaseDatabase.getInstance();
                DatabaseReference MyRefff = data.getReference("Users");

                UpdateClass updateClass =new UpdateClass(

                        passHeight,
                        passWeight,
                        pal
                );


                Map<String,Object> map = updateClass.toMap();
                MyRefff.child(userid).updateChildren(map);




                double bmi, temp, check=1;

            temp = heightincm / 100;
            check = temp * temp;
            bmi = (weightinkg / check);
            String dc = new DecimalFormat("##.##").format(bmi);
            tvshowbmi.setText("Your BMI is : " + dc);

           //bmi status
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
            case R.id.btnhistory:{
               //redirect to histiry Page

                Intent i = new Intent(bmiopetation.this, HistoryPage.class);
                startActivity(i);
                break;
            }

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
