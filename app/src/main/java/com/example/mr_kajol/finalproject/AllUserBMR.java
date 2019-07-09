package com.example.mr_kajol.finalproject;

import android.arch.core.executor.TaskExecutor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AllUserBMR extends AppCompatActivity implements View.OnClickListener {

    private EditText Height, Weight, age;
    private Button BMR;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_bmr);
        Height = findViewById(R.id.height);
        Weight = findViewById(R.id.weight);
        age = findViewById(R.id.Age);
        BMR = findViewById( R.id.BMR);
        tv = findViewById(R.id.tv);

        BMR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //BMR (metric) = (10 × weight in kg) + (6.25 × height in cm) - (5 × age in years) + 5

        switch (v.getId()){

            case R.id.BMR:{

                Double Hei = Double.parseDouble(Height.getText().toString());
                Double Wei = Double.parseDouble(Weight.getText().toString());
                Double Ag = Double.parseDouble(age.getText().toString());
                Double BM;

                BM = (10 * Hei) + (6.25*Wei) - (5 * Ag) + 5 ;
                String temp = BM.toString();

                tv.setText(Height.toString());

                break;

            }
        }



    }
}
