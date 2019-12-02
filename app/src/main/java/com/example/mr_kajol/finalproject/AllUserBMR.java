package com.example.mr_kajol.finalproject;

import android.arch.core.executor.TaskExecutor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class AllUserBMR extends AppCompatActivity implements View.OnClickListener {

    private EditText Height, BWeight, age;
    private Button BMRBTN;
    private TextView tv;
    private Spinner Gender, ActivityLe;
    int genderindex, activityindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user_bmr);

        Height = findViewById(R.id.BMRHeight);
        BWeight = findViewById(R.id.BMRWeight);
        age = findViewById(R.id.Age);
        BMRBTN = findViewById( R.id.BMR);
        tv = findViewById(R.id.tv);
        Gender = findViewById(R.id.gender);
        ActivityLe = findViewById(R.id.activitylevel);

        BMRBTN.setOnClickListener(this);


        ArrayAdapter<CharSequence>AcitivityAdapter = ArrayAdapter.createFromResource(this,R.array.PalLevel, android.R.layout.simple_spinner_item);
        AcitivityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ActivityLe.setAdapter(AcitivityAdapter);

        ArrayAdapter<CharSequence> Sexadapter = ArrayAdapter.createFromResource(this,R.array.SEX, android.R.layout.simple_spinner_item);
        Sexadapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Gender.setAdapter(Sexadapter);

        Gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                genderindex = position;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        ActivityLe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activityindex = position;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }

    @Override
    public void onClick(View v) {

      /*  Mifflin-St Jeor Equation:
        For men:
        BMR = 10W + 6.25H - 5A + 5
        For women:
        BMR = 10W + 6.25H - 5A - 161*/

        switch (v.getId()){

            case R.id.BMR:{
                try{

                    Double Hei = Double.parseDouble(Height.getText().toString());
                    Double Wei = Double.parseDouble(BWeight.getText().toString());
                    Double Ag = Double.parseDouble(age.getText().toString());
                    Double BM=1.0, CalNeed=1.0;


                    if(genderindex == 0){
                        BM = (10 * Hei) + (6.25*Wei) - (5 * Ag) + 5 ;
                    }
                    else if(genderindex == 1){
                        BM = (10 * Hei) + (6.25*Wei) - (5 * Ag) - 161 ;
                    }

                    if(activityindex == 0)
                        CalNeed = BM * 1.2;
                        else if(activityindex == 1 )
                            CalNeed = BM * 1.375;
                            else if (activityindex == 2)
                                 CalNeed = BM * 1.55;
                                    else if (activityindex == 3)
                                        CalNeed = BM * 1.725;
                                        else if (activityindex == 4)
                                            CalNeed = BM * 1.9;


                    String bmr = new DecimalFormat("##.##").format(BM);
                    String Calories = new DecimalFormat("##.##").format(CalNeed);
                    tv.setText("Your BMR is : " + bmr + "\n" + " Daily Calories Needed: "+ Calories + "\n");

                }catch (Exception e){ }

                break;

            }
        }


    }
}
