package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserView extends AppCompatActivity implements View.OnClickListener {

    private CardView BMI, BMR, KCAL,PAL, signin,signup;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        BMI =findViewById(R.id.Bmiutton);
        signup =findViewById(R.id.signup);
        signin =findViewById(R.id.SignIn);
        BMR = findViewById(R.id.BMRcardviewID);

        BMI.setOnClickListener(this);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
        BMR.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
            Intent i = new Intent(UserView.this, HomeActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.Bmiutton:{
                Intent intent = new Intent(UserView.this, AlluserBmiCal.class);
                startActivity(intent);
                break;
            }

            case R.id.signup:{
                Intent intent = new Intent(UserView.this, CreateAccount.class);
                startActivity(intent);
                break;
            }
            case R.id.SignIn:{
                Intent intent = new Intent(UserView.this, MainActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.BMRcardviewID:{

                Intent intent = new Intent(UserView.this, ViewRecycle.class);
                startActivity(intent);
                break;
            }

        }

    }
}
