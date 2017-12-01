package com.android.proyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {

    private ProgressBar progresplash;
    int progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        progresplash = (ProgressBar) findViewById(R.id.progresplash);

        new  Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<4; i++ ){
                    progress += 25;
                    progresplash.setProgress(progress);
                    if(progress == progresplash.getMax()){
                        Intent intent = new Intent(SplashScreen.this, IntroActivity.class);
                        startActivity(intent);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
  finish();
            }
        },4000);
    }
}
