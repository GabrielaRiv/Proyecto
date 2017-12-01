package com.android.proyecto.paginas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.proyecto.Perfil;
import com.android.proyecto.R;
import com.android.proyecto.clases.Informacion;
import com.android.proyecto.clases.Marcador;
import com.android.proyecto.clases.MarcadoresSingleton;
import com.android.proyecto.clases.ShareInformacion;
import com.android.proyecto.galeria.gupam;
import com.android.proyecto.mapa.MapaActivity;
import com.android.proyecto.universidades.Acercade;
import com.android.proyecto.universidades.Ahuachapan;
import com.google.android.gms.maps.model.LatLng;

public class upam extends AppCompatActivity {
    TextView id, name, mision, vision, carreras, telefono, direccion, web;
    TextView txt1, txt2, txt3, txt4, txt5, txt6;
    int option;
    Button mapa;
   // private Toolbar toolbar;
    //private SectionsPagerAdapter mSectionsPagerAdapter;
   // private ViewPager mViewPager;
    private Typeface script1;
    private Typeface script3;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent atras = new Intent(this, Ahuachapan.class);
            startActivity(atras);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upam);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mapa = (Button) findViewById(R.id.mapa);
        id = (TextView) findViewById(R.id.textViewIdupam);
        name = (TextView) findViewById(R.id.nombreupam);
        mision = (TextView) findViewById(R.id.misionupam);
        vision = (TextView) findViewById(R.id.visionupam);
        carreras = (TextView) findViewById(R.id.carrerasupam);
        telefono = (TextView) findViewById(R.id.telefonoupam);
        direccion = (TextView) findViewById(R.id.direccionupam);
        web = (TextView)findViewById(R.id.webupam);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);

        Informacion informe =  ShareInformacion.getInstance(this).getInformacion();

        id.setText(String.valueOf(informe.getIduniversidad()));
        name.setText(informe.getNombre());
        mision.setText(informe.getDescripcion());
        vision.setText(informe.getVision());
        carreras.setText(informe.getCarreras());
        telefono.setText(informe.getTelefono());
        direccion.setText(informe.getDireccion());
        web.setText(informe.getWeb());

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapa = new Intent(upam.this, MapaActivity.class);
                Marcador m = MarcadoresSingleton.getInstance().getMarcador("UPAM");
                mapa.putExtra("MARCADOR",m);
                startActivity(mapa);
            }
        });

        //para las fuentes de las letras
        String fuente1 ="fuentes/Black.otf";
        String fuente3 ="fuentes/Regular.otf";
        this.script1 = Typeface.createFromAsset(getAssets(), fuente1);
        this.script3 = Typeface.createFromAsset(getAssets(), fuente3);
        name.setTypeface(script1);
        mision.setTypeface(script3);
        vision.setTypeface(script3);
        carreras.setTypeface(script3);
        telefono.setTypeface(script3);
        direccion.setTypeface(script3);
        txt1.setTypeface(script3);
        txt2.setTypeface(script3);
        txt3.setTypeface(script3);
        txt4.setTypeface(script3);
        txt5.setTypeface(script3);
        txt6.setTypeface(script3);
    }
//para el menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LatLng islamabad;
        switch (item.getItemId()) {
            case R.id.perfil:
                Intent intent = new Intent(upam.this, Perfil.class);
                startActivity(intent);
                break;

            case R.id.acerca:
                Intent intent1 = new Intent(upam.this, Acercade.class);
                startActivity(intent1);
                break;

            case R.id.galeria:
                Intent intent2 = new Intent(upam.this, gupam.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
