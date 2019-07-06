package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView BMI, BMR, KCAL, FS,History,PAL, Logout, Updateprof;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       BMI =findViewById(R.id.Bmiutton);
       History =findViewById(R.id.HistoryBtn);
       Logout = findViewById(R.id.Logout);
       Updateprof = findViewById(R.id.UpdateProfile);

        BMI.setOnClickListener(this);
        History.setOnClickListener(this);
        Logout.setOnClickListener(this);
        Updateprof.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


       if(mAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }

        //getting current user
        FirebaseUser user = mAuth.getCurrentUser();

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.Bmiutton:{
                Intent intent = new Intent(HomeActivity.this,bmiopetation.class);
                startActivity(intent);
                break;
            }
            case R.id.HistoryBtn:{
                Intent intent = new Intent(HomeActivity.this, HistoryPage.class);
                startActivity(intent);
                break;
            }

         case R.id.signup:{

                Intent intent = new Intent(HomeActivity.this, CreateAccount.class);
                startActivity(intent);
                break;
            }
            case R.id.SignIn:{

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.UpdateProfile:{

                Intent intent = new Intent(HomeActivity.this, UpdateProfile.class);
                startActivity(intent);
                break;
            }
            case R.id.Logout:{

                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, UserView.class);
                startActivity(intent);
                break;
            }
        }

    }
}
