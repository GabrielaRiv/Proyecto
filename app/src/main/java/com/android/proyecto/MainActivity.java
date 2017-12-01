package com.android.proyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.proyecto.clases.MarcadoresSingleton;
import com.android.proyecto.clases.SharedPrefManager;

public class MainActivity extends AppCompatActivity {
    Button ingreso;
    Button registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO en este momento lleno los marcadores para poder usarlos dentro de toda la app



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.ingreso = (Button) findViewById(R.id.ingreso);
        this.registro = (Button) findViewById(R.id.nuevo);

        if (SharedPrefManager.getInstance(MainActivity.this).isLoggedIn()) {
            finish();
            startActivity(new Intent(MainActivity.this, Departamentos.class));
            return;
        }

        ingreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ingreso = new Intent(MainActivity.this, Login.class);
                startActivity(ingreso);

            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registro = new Intent(MainActivity.this, Registro.class);
                startActivity(registro);
            }
        });

    }
}
