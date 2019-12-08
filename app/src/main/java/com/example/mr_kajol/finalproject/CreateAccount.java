package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Math.floor;


public class CreateAccount extends AppCompatActivity  implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword, editTextPhone, Age;
    private ProgressBar progressBar;
    Spinner genderspiner;

    private FirebaseAuth mAuth;
    int genderindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextName = findViewById(R.id.edit_text_name);
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextPhone = findViewById(R.id.edit_text_phone);
        Age = findViewById(R.id.UserAge);
        genderspiner = findViewById(R.id.genderspiner);



        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.backtologin).setOnClickListener(this);


        // GENDER
        ArrayAdapter<CharSequence> gender = ArrayAdapter.createFromResource(this,R.array.SEX, android.R.layout.simple_spinner_item);
        gender.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderspiner.setAdapter(gender);

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
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String phone = editTextPhone.getText().toString().trim();
        final String age = Age.getText().toString().trim();



        if (name.isEmpty()) {
            editTextName.setError(getString(R.string.input_error_name));
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError(getString(R.string.input_error_email));
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError(getString(R.string.input_error_email_invalid));
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError(getString(R.string.input_error_password));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError(getString(R.string.input_error_password_length));
            editTextPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_phone));
            editTextPhone.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            editTextPhone.setError(getString(R.string.input_error_age));
            editTextPhone.requestFocus();
            return;
        }
        if (phone.length() != 11) {
            editTextPhone.setError(getString(R.string.input_error_phone_invalid));
            editTextPhone.requestFocus();
            return;
        }



        // progressBar.setVisibility(View.VISIBLE);
         final String height="",weight="", pal = "00";

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            String sex="";

                            if(genderindex == 0){
                                sex ="Male";
                            }
                            else if(genderindex == 1){
                                sex = "Female";
                            }

                            User user = new User(
                                    name,
                                    email,
                                    phone,
                                    password,
                                    height,
                                    weight,
                                    sex,
                                    age,
                                    pal
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(CreateAccount.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();

                                        Intent i = new Intent(CreateAccount.this, HomeActivity.class);
                                         startActivity(i);
                                    } else {
                                        //display a failure message
                                        Toast.makeText(CreateAccount.this, "Registration Failed", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(CreateAccount.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_register:{
                registerUser();
                break;
            }
            case R.id.backtologin:{
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            }

        }
    }

}
