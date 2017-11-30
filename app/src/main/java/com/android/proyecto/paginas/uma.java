package com.android.proyecto.paginas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.android.proyecto.galeria.guma;
import com.android.proyecto.mapa.MapaActivity;
import com.android.proyecto.universidades.Acercade;
import com.android.proyecto.universidades.Sonsonate;
import com.google.android.gms.maps.model.LatLng;

public class uma extends AppCompatActivity {
    TextView id, name, mision, vision, carreras, telefono, direccion, web, latitud, longitud;
    TextView txt1, txt2, txt3, txt4, txt5, txt6;
    int option;
    Button mapa;
    private Toolbar toolbar;
  //  private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Typeface script1;
    private Typeface script3;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent atras = new Intent(this, Sonsonate.class);
            startActivity(atras);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uma);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mapa = (Button) findViewById(R.id.mapa);
        id = (TextView) findViewById(R.id.textViewIduma);
        name = (TextView) findViewById(R.id.nombreuma);
        mision = (TextView) findViewById(R.id.misionuma);
        vision = (TextView) findViewById(R.id.visionuma);
        carreras = (TextView) findViewById(R.id.carrerasuma);
        telefono = (TextView) findViewById(R.id.telefonouma);
        direccion = (TextView) findViewById(R.id.direccionuma);
        web = (TextView)findViewById(R.id.webuma);
        latitud = (TextView) findViewById(R.id.latituduma);
        longitud = (TextView) findViewById(R.id.longituduma);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        txt5 = (TextView) findViewById(R.id.txt5);
        txt6 = (TextView) findViewById(R.id.txt6);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Informacion informe =  ShareInformacion.getInstance(this).getInformacion();


        id.setText(String.valueOf(informe.getIduniversidad()));
        name.setText(informe.getNombre());
        mision.setText(informe.getDescripcion());
        vision.setText(informe.getVision());
        carreras.setText(informe.getCarreras());
        telefono.setText(informe.getTelefono());
        direccion.setText(informe.getDireccion());
        web.setText(informe.getWeb());
        latitud.setText(informe.getLatitud());
        longitud.setText(informe.getLongitud());

      //  mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
      //  mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapa = new Intent(uma.this, MapaActivity.class);
                Marcador m = MarcadoresSingleton.getInstance().getMarcador("UMA");
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
        latitud.setTypeface(script3);
        longitud.setTypeface(script3);
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
                Intent intent = new Intent(uma.this, Perfil.class);
                startActivity(intent);
                break;

            case R.id.acerca:
                Intent intent1 = new Intent(uma.this, Acercade.class);
                startActivity(intent1);
                break;

            case R.id.galeria:
                Intent intent2 = new Intent(uma.this, guma.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
