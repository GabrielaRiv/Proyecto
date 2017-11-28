package com.android.proyecto.Mapas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.android.proyecto.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapaitca extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapaitca);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng coordenadas = new LatLng(13.8240738, -89.5715033 );
        mMap.addMarker(new MarkerOptions().position(coordenadas).title("ITCA"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordenadas));
    }

    //segundo creo un metodo para agregar un marcador en el mapa creo un obj LatLng para incluir latitud y longitud
    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, -lng );
        //utilizamos el elemento CameraUpdate centrando la camara en la posicion de el marker
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        //si el marcador es diferente de nulo se debera de remover
        if (marcador != null) marcador.remove();
        //le agrego propiedades al marker como titulo, imagen...
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Mi posicion actual")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        //agrego el metodo animateCamera
        mMap.animateCamera(miUbicacion);
    }

    //tercero creo unmetodo para obtener latitud y longitud actual utilizando metodos de la clase location utilizado como parametro del metodo
    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    //cuarto implementar un objeto tipo LocationListener el cual esta atento a los cambios de localidad recibidos por el GPS
    //este objeto crea los metodos asociados a los distintos eventos del proveedor de localizacion
    LocationListener locListener = new LocationListener() {
        //aqui llamaremos a nuestro metodo actualizarUbicacion ya que este se lanza cada vez que recibe una actualizacion
        @Override
        public void onLocationChanged(Location location) {

            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    //quinto creo metodo para hacer referencia a la clase LocationManager utilizada para tener servicion de geoposicionamiento
    //mediante el metodo getLastKnowLocation
    private void miUbicacion() {
        //permiso creado por el sistema
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        //mandamos a llamar al metodo actualizarUbicacion mediante el metodo requestLocationUpdates solicitando al GPS actualizaciones cada 15seg
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 15000,0, locListener);
    }//paso final arriba en onMapReady
}
