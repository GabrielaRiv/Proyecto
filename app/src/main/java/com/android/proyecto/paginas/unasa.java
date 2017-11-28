package com.android.proyecto.paginas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.android.proyecto.clases.ShareInformacion;
import com.android.proyecto.mapa.MapaActivity;
import com.android.proyecto.universidades.SantaAna;

public class unasa extends AppCompatActivity {
    TextView id, name, mision, vision, carreras, telefono, direccion, web, latitud, longitud;
    int option;
    Button mapa;
    private Toolbar toolbar;
   // private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent atras = new Intent(this, SantaAna.class);
            startActivity(atras);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unasa);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mapa = (Button) findViewById(R.id.mapa);
        id = (TextView) findViewById(R.id.textViewIdunasa);
        name = (TextView) findViewById(R.id.nombreunasa);
        mision = (TextView) findViewById(R.id.misionunasa);
        vision = (TextView) findViewById(R.id.visionunasa);
        carreras = (TextView) findViewById(R.id.carrerasunasa);
        telefono = (TextView) findViewById(R.id.telefonounasa);
        direccion = (TextView) findViewById(R.id.direccionunasa);
        web = (TextView)findViewById(R.id.webunasa);
        latitud = (TextView) findViewById(R.id.latitudunasa);
        longitud = (TextView) findViewById(R.id.longitudunasa);
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

       // mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
       // mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapa = new Intent(unasa.this, MapaActivity.class);
                startActivity(mapa);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activiry, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent intent = new Intent(unasa.this, Perfil.class);
        startActivity(intent);
        return true;
    }

   /* public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static catolica.PlaceholderFragment newInstance(int sectionNumber) {
            catolica.PlaceholderFragment fragment = new catolica.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fragmentvalores, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText((getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER))));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return catolica.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "VALORES";
                case 1:
                    return "CARRERAS";
                case 2:
                    return "CONTACTOS";
            }
            return null;
        }
    }*/
}
