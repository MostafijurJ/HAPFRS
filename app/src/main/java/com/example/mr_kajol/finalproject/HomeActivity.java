package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BMI, BMR, KCAL, FS,History,PAL, signin,signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BMI =findViewById(R.id.Bmiutton);
        BMR =findViewById(R.id.BMRButton);
        KCAL =findViewById(R.id.CalButton);
        FS =findViewById(R.id.FoodSuggestBtn);
        History =findViewById(R.id.HistoryBtn);
        PAL =findViewById(R.id.PALButton);
        signin =findViewById(R.id.SignIn);
        signup =findViewById(R.id.signup);

        BMI.setOnClickListener(this);
        BMR.setOnClickListener(this);
        KCAL.setOnClickListener(this);
        FS.setOnClickListener(this);
        History.setOnClickListener(this);
        PAL.setOnClickListener(this);
        signin.setOnClickListener(this);
        signup.setOnClickListener(this);

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
        }

    }
}
