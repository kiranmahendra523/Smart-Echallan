package com.example.smartechallan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        Thread th = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent in = new Intent(SplashAct.this,MainActivity.class);
                    startActivity(in);
                }
            }
        };th.start();
    }
}