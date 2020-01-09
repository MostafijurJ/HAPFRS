package com.example.mr_kajol.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class AppOpening extends AppCompatActivity {

    private ProgressBar progressBar;
    private  Integer progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_app_opening);
        progressBar = findViewById(R.id.progressbasID);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                StartApk();
            }
        });
        thread.start();
    }

    public void doWork(){

        for(progress=20; progress<=100; progress+=20) {
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void StartApk(){
        Intent intent = new Intent(AppOpening.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
