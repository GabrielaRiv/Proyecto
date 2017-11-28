package com.android.proyecto.mapa;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.proyecto.R;
import com.android.proyecto.clases.Marcador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback {

    //mapa de google
    private GoogleMap mMap;
    //marcador de la universidad
    private Marcador marcadorUNI;
    //zoom por defecto
    //Default zoom
    private static final int DEFAULT_ZOOM = 15;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        marcadorUNI = (Marcador) this.getIntent().getExtras().getSerializable("MARCADOR");
        setContentView(R.layout.activity_mapa);

        //para el mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //cuando el mapa este listo agrego el marcador de la universidad
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (marcadorUNI != null) {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(marcadorUNI.latitud, marcadorUNI.longitud))
                    .title(marcadorUNI.titulo)
                    .icon((BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));
            LatLng islamabad = new LatLng(marcadorUNI.latitud, marcadorUNI.longitud);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islamabad, DEFAULT_ZOOM));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
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
            case R.id.item_miUbicacion:
                //islamabad = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islamabad,ZOOM));
                break;

            case R.id.item_universidad:
                islamabad = new LatLng(marcadorUNI.latitud, marcadorUNI.longitud);
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islamabad,DEFAULT_ZOOM));
                break;
            case R.id.item_ruta:
                //obtenerRuta();
                break;
            case R.id.item_actualizar:
                //actualizarPosicion();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
