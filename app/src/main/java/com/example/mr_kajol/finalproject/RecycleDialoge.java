package com.example.mr_kajol.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mr_kajol.finalproject.DialogeClass;

public class RecycleDialoge extends AppCompatActivity implements DialogeClass.ExampleDialogListener {

    TextView textViewUsername;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_dialoge);

       // button = (Button) findViewById(R.id.ChooseBtn);
      /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });*/
    }

    public void openDialog() {
        DialogeClass exampleDialog = new DialogeClass();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void applyTexts(String username) {
        textViewUsername.setText(username);
    }
}