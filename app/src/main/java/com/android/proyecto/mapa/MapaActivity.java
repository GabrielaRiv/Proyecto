package com.android.proyecto.mapa;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.android.proyecto.R;
import com.android.proyecto.clases.Marcador;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

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
        marcadorUNI= (Marcador) this.getIntent().getExtras().getSerializable("MARCADOR");
        setContentView(R.layout.activity_mapa);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(marcadorUNI!=null){
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(marcadorUNI.latitud, marcadorUNI.longitud))
                    .title(marcadorUNI.titulo)
                    .icon((BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))));
            LatLng islamabad = new LatLng(marcadorUNI.latitud,marcadorUNI.longitud);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islamabad,DEFAULT_ZOOM));
        }
    }
}
