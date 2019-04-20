package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginbtn,Photo;
    Button signupbtn;
    EditText LoginUserName, LoginUserPAss;
    TextView tv;
    private ListView listView;
    private FirebaseAuth mAuth;

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginbtn =findViewById(R.id.loginbtn);
        signupbtn =findViewById(R.id.signupbtn);
        Photo = findViewById(R.id.photo);


        LoginUserName =findViewById(R.id.LoginUserName);
        LoginUserPAss =findViewById(R.id.LoginUserPass);

        loginbtn.setOnClickListener(this);
        signupbtn.setOnClickListener(this);
        Photo.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //handle the already login user

            Intent i = new Intent(MainActivity.this, bmiopetation.class);
            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.loginbtn: {

                String UserName = LoginUserName.getText().toString();
                String UserPass = LoginUserPAss.getText().toString();

                if (TextUtils.isEmpty(UserName)) {
                    Toast.makeText(MainActivity.this, "Enter Valid UserName", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(UserPass)) {
                    Toast.makeText(MainActivity.this, "Enter the correct password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(UserName,UserPass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                //if the task is successfull
                                if(task.isSuccessful()){
                                    //start the profile activity
                                    finish();
                                    startActivity(new Intent(getApplicationContext(), bmiopetation.class));
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Email Pass Invalid", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
             break;
            }
            case R.id.signupbtn:{

               Intent i = new Intent(this, CreateAccount.class);
                startActivity(i);
               break;
            }
            case R.id.photo:{

               Intent i = new Intent(this, photoes.class);
                startActivity(i);
               break;
            }

        }

    }


}

