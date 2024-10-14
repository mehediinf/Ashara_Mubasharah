package com.ashara_mubasharah.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

private ProgressBar progressBar;

int progressNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBarId);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //Progress Method call
                doWprk();

                //Next Page Call
                startApp();

            }
        });
        thread.start();



    }
    public void doWprk(){
        for (progressNum=20;progressNum<=100;progressNum+=20){
            try {
                Thread.sleep(600);
                progressBar.setProgress(progressNum);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void startApp(){
        startActivity(new Intent(SplashScreen.this,MainActivity.class));
        finish();
    }
}