package com.android.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.android.proyecto.clases.MarcadoresSingleton;
import com.android.proyecto.universidades.Acercade;
import com.android.proyecto.universidades.Ahuachapan;
import com.android.proyecto.universidades.SantaAna;
import com.android.proyecto.universidades.Sonsonate;

public class Departamentos extends AppCompatActivity {
    LinearLayout Sonsonate;
    LinearLayout SantaAna;
    LinearLayout Ahuachapan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamentos);

        SantaAna  = (LinearLayout) findViewById(R.id.SantaAna);
        Sonsonate = (LinearLayout) findViewById(R.id.Sonsonate);
        Ahuachapan = (LinearLayout) findViewById(R.id.Ahuachapan);

        MarcadoresSingleton.getInstance().llenarMarcadores();

        Sonsonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sonsonate = new Intent(Departamentos.this, Sonsonate.class);
                startActivity(sonsonate);
            }
        });

        SantaAna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent santana = new Intent(Departamentos.this, SantaAna.class);
                startActivity(santana);
            }
        });

        Ahuachapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ahuachapan = new Intent(Departamentos.this, Ahuachapan.class);
                startActivity(ahuachapan);
            }
        });
    }

    //para el menu
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.perfil:
                Intent intent1 = new Intent(Departamentos.this, Perfil.class);
                startActivity(intent1);
                break;

            case R.id.acerca:
                Intent intent2 = new Intent(Departamentos.this, Acercade.class);
                startActivity(intent2);
                break;

            case R.id.infor:
                Intent intent3 = new Intent(Departamentos.this, Instrucciones.class);
                startActivity(intent3);
        }
        return super.onOptionsItemSelected(item);
    }
}
