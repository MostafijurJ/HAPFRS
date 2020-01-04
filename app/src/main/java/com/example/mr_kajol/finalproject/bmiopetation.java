package com.example.mr_kajol.finalproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;


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


import static android.widget.Toast.LENGTH_LONG;
import static java.lang.Math.floor;

public class bmiopetation extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    Button bmibtn, ShowFood,buttonLogout;
    EditText height;
    EditText weight;

    TextView tvheight;
    TextView tvshowbmi;
    TextView tvweight;
    TextView heightTv;
    TextView weightTv;
    TextView tvbmistatus;
    TextView showdata;
    TextView TVLastModifiedDate, TVSetUserName;

    Spinner heightunits;
    Spinner weightunits;
    Spinner palspiner;

    TextView Nav_Name, Nav_Email;

    private RadioGroup radioGroup;
    private RadioButton radioGolbtn;

    private   final int PICK_IMAGE_REQUEST  = 1;

    private  FirebaseAuth mAuth;

    int heightindex, weightindex,palindex;
    Double heightincm, weightinkg;

    private  String Height="", Weight="",  UserAge="", Gender = "",LastDate= "", UserName="", UserEmail = "";
    Double NetCAL=0.0;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    android.support.v7.widget.Toolbar toolbar;
    NavigationView navigationView;

    @SuppressLint("RestrictedApi")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiopetation);

       // Historybtn = findViewById(R.id.btnhistory);
        bmibtn = findViewById(R.id.btnbmi);
        ShowFood = findViewById(R.id.showfood);


        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

        tvshowbmi = findViewById(R.id.tvshowbmi);
        tvbmistatus = findViewById(R.id.bmistatus);

        Nav_Name = findViewById(R.id.Nav_personname);
        Nav_Email = findViewById(R.id.Nav_personemail);

        tvbmistatus = findViewById(R.id.bmistatus);
        tvbmistatus = findViewById(R.id.bmistatus);



        heightunits = findViewById(R.id.heightunits);
        weightunits = findViewById(R.id.weightunits);
        palspiner = findViewById(R.id.palspiner);
        showdata = findViewById(R.id.showdata);
        TVLastModifiedDate = findViewById(R.id.tvlastmodified);
        TVSetUserName = findViewById(R.id.setusername);
        radioGroup = findViewById(R.id.radiogroup);


        /// Nevigation Bar
        drawerLayout=findViewById(R.id.drawableId);
        toolbar=findViewById(R.id.Toolbar);
        navigationView=findViewById(R.id.navigationId);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);


        navigationView.setNavigationItemSelectedListener((OnNavigationItemSelectedListener) this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.nv, R.string.nn);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();





        mAuth = FirebaseAuth.getInstance();




        if(mAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

        //getting current user
        FirebaseUser user = mAuth.getCurrentUser();

       // buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        //showdata.setText("Welcome : "+user.getEmail());

        bmibtn.setOnClickListener(this);
        ShowFood.setOnClickListener(this);

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
       /// FirebaseUser FUser = mAuth.getCurrentUser();
        String CurrentUserId = user.getUid();
        DatabaseReference DR;
        DR = FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserId);
        DR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Height = dataSnapshot.child("Height").getValue().toString();
                 Weight = dataSnapshot.child("Weight").getValue().toString();
                 UserAge = dataSnapshot.child("Age").getValue().toString();
                Gender = dataSnapshot.child("Sex").getValue().toString();
                LastDate = dataSnapshot.child("Date").getValue().toString();
                UserName = dataSnapshot.child("Name").getValue().toString();
                UserEmail = dataSnapshot.child("Email").getValue().toString();

                if(Height==null) Height = "00";
                else if(Weight==null)Weight= "00";
                else if(LastDate==null) LastDate ="Today";

                height.setText(Height);
                weight.setText(Weight);
                TVLastModifiedDate.setText(LastDate);
                TVSetUserName.setText("Mr/Mrs "+UserName);

//               Nav_Email.setText(UserEmail);
//               Nav_Name.setText(UserName);


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
                    palscore = 1.2;
                }
                else if(palindex == 1){
                  palscore = 1.375;
                } else if(palindex == 2){
                  palscore = 1.55;
                }else if(palindex == 3){
                  palscore = 1.725;
                }
                else if(palindex == 4){
                  palscore = 1.9;
                }

                String pal = new DecimalFormat("##.##").format(palscore).trim();

                String passHeight =  HighttoStore;
                String passWeight =  WeighttoStore;

                FirebaseUser user = mAuth.getCurrentUser();


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
                DR = FirebaseDatabase.getInstance().getReference().child("Users").child(Uuserid);
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
                    Toast.makeText(getApplicationContext()," Data are same", LENGTH_LONG).show();
                }else{
                    Map<String, String> HistoryMap = new HashMap<String, String>();
                    // For History Checking  Height Weight
                    HistoryMap.put("Height : ", passHeight+" cm");
                    HistoryMap.put("Weight : ", passWeight+ " Kg");
                    HistoryMap.put("Date : ", date);
                    HistoryMap.put("PAL : ", pal);
                    HistoryReff.child(Uuserid).push().setValue(HistoryMap);
                }

                // Updating User table's value
                FirebaseDatabase data = FirebaseDatabase.getInstance();
                DatabaseReference MyRefff = data.getReference("Users");

                UpdateClass updateClass =new UpdateClass(

                        passHeight,
                        passWeight,
                        pal,
                        date
                );


                Map<String,Object> map = updateClass.toMap();
                MyRefff.child(Uuserid).updateChildren(map);


                /// (Men) BMR (metric) = (10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) + 5
                /// (Women)  BMR (metric) = (10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) - 161

                double DAge = Double.parseDouble(UserAge);
                String Male = "Male";
                Double bmr, Calorie;
                if(Gender ==Male){
                    bmr = (10 * weightinkg) + (6.25 * heightincm) - (5 * DAge) + 5;
                } else bmr = (10 * weightinkg) + (6.25 * heightincm) - (5 * DAge) -161;


                Calorie = bmr * palscore;
                String CAL = new DecimalFormat("##.").format(Calorie);
                tvshowbmi.setText("Your Required Calorie is : " + CAL);

                 NetCAL = Calorie * 0.8;


                /// User Goal Checking with Radio Button
                int selectedId = radioGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioGolbtn = (RadioButton) findViewById(selectedId);
                String radioString = radioGolbtn.getText().toString();
                //tvbmistatus.setText(radioString);

               /* if (radioString.isEmpty()) {
                    radioGolbtn.setError(getString(R.string.input_error_name));
                    radioGolbtn.requestFocus();
                    return;
                }*/

                if (TextUtils.isEmpty(radioString)) {
                    Toast.makeText(bmiopetation.this, "Choose your Goal Please", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(radioString.equals("Lose Weight"))
                    NetCAL -= 500.00;
                else if(radioString.equals("Gain Weight"))
                    NetCAL += 500.00;

                String NeCAL = new DecimalFormat("##.").format(NetCAL);
                showdata.setText("We Can Distribute Calorie  : " + NeCAL);

                showDialog();
                break;
        }
            case R.id.showfood:{

                String NeCAL = new DecimalFormat("##.").format(NetCAL);
                Intent intent = new Intent(bmiopetation.this, ViewRecycle.class);

                Bundle bundle = new Bundle();
                //Add your data to bundle
                bundle.putString("NetCAL", NeCAL );

                //Add the bundle to the intent
                intent.putExtras(bundle);

                startActivity(intent);
                break;
            }


        }

    }


    public void showDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(bmiopetation.this);
        alert.setTitle("Choose you Food");

        alert.setPositiveButton("tab here", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                ChoosefoodDialog();
            }
        });

        alert.create().show();
    }



    public void ChoosefoodDialog() {
        String NeCAL = new DecimalFormat("##.").format(NetCAL);
        Intent intent = new Intent(bmiopetation.this, ViewRecycle.class);

        Bundle bundle = new Bundle();
        //Add your data to bundle
        bundle.putString("NetCAL", NeCAL );

        //Add the bundle to the intent
        intent.putExtras(bundle);

        startActivity(intent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



        if(item.getItemId()==R.id.homeMenuId) {
            Intent intent=new Intent(this,UserView.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.ChooseFoodMenu) {
            Intent intent=new Intent(this,UpdateProfile.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.profileMenuId) {
            Intent intent=new Intent(this,bmiopetation.class);
            startActivity(intent);
        }
        if(item.getItemId()==R.id.CheckoutHistoryMenu) {
            Intent intent=new Intent(this,HistoryPage.class);
            startActivity(intent);
        }

        if(item.getItemId()==R.id.LogoutMenu) {
            mAuth.signOut();
            Intent intent=new Intent(this,UserView.class);
            startActivity(intent);
        }
         if(item.getItemId()==R.id.AboutUSMenu) {
                    mAuth.signOut();
                    Intent intent=new Intent(this, AboutUs.class);
                    startActivity(intent);
                }


        return false;
    }
}
