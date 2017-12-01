package com.android.proyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.proyecto.clases.SharedPrefManager;
import com.android.proyecto.clases.User;

public class Perfil extends AppCompatActivity {
    TextView textViewUsername, textViewEmail;
    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       // toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //if the user is not logged in
        //starting the login activity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        }



        textViewUsername = (TextView) findViewById(R.id.textViewUsername);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);


        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textViewUsername.setText(user.getUsername());
        textViewEmail.setText(user.getEmail());

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });
    }
}
