package com.android.proyecto.paginas;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.proyecto.Perfil;
import com.android.proyecto.R;
import com.android.proyecto.mapa.MapaActivity;
import com.android.proyecto.universidades.SantaAna;


public class catolica extends AppCompatActivity {
    //TextView id, name, mision, vision, carreras, telefono, direccion, web;
    int option;
    Button mapa;
    private Toolbar toolbar;
    private SectionsPagerAdapter mSectionsPagerAdapter;
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
        setContentView(R.layout.activity_catolica);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

      //  id = (TextView) findViewById(R.id.textViewIdcatolica);
      //  name = (TextView) findViewById(R.id.nombrecatolica);
      //  mision = (TextView) findViewById(R.id.misioncatolica);
      //  vision = (TextView) findViewById(R.id.visioncatolica);
     //   carreras = (TextView) findViewById(R.id.carrerascatolica);
      //  telefono = (TextView) findViewById(R.id.telefonocatolica);
      //  direccion = (TextView) findViewById(R.id.direccioncatolica);
     //   web = (TextView)findViewById(R.id.webcatolica);
        mapa = (Button) findViewById(R.id.mapa);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

       // Informacion informe =  ShareInformacion.getInstance(this).getInformacion();


      //  id.setText(String.valueOf(informe.getIduniversidad()));
     //   name.setText(informe.getNombre());
     //   mision.setText(informe.getDescripcion());
     //   vision.setText(informe.getVision());
     //   carreras.setText(informe.getCarreras());
     //   telefono.setText(informe.getTelefono());
     //   direccion.setText(informe.getDireccion());
     //   web.setText(informe.getWeb());

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mapa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mapa = new Intent(catolica.this, MapaActivity.class);
                    startActivity(mapa);
                }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_activiry, menu);
            return true;
        }
        public boolean onOptionsItemSelected(MenuItem menuItem){
            Intent intent = new Intent(catolica.this, Perfil.class);
            startActivity(intent);
            return true;
        }


    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fragmentvalores, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.textViewId);
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
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    fragmentvalores fv = new fragmentvalores();
                    return fv;
                case 1:
                    fragmentcarreras fc = new fragmentcarreras();
                    return fc;
                case 2:
                    fragmentcontactos fcon = new fragmentcontactos();
                    return fcon;
            }
            return null;
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
    }

}

