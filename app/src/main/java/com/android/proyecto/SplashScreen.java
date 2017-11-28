package com.android.proyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.proyecto.universidades.SantaAna;

public class SplashScreen extends AppCompatActivity {
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        img = (ImageView) findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pan = new Intent(SplashScreen.this, Departamentos.class);
                startActivity(pan);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        },3000);
    }
}
