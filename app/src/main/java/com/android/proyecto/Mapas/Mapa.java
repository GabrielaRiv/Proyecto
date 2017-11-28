package com.android.proyecto.Mapas;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.android.proyecto.R;
import com.android.proyecto.clases.Marcador;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class Mapa extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleMap mMap;
    //primer paso declaramos un objeto Marker marcador y dos variables para la latitud y longitud
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    //CameraPosition para manejar la camara
    private CameraPosition mCameraPosition;
    //Default zoom
    private static final int DEFAULT_ZOOM = 15;
    //variable para pedir el permiso
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 14;
    //acceso al GPS
    private GoogleApiClient mGoogleApiClient;
    //si el permiso fue autorizado
    private boolean mLocationPermissionGranted;
    //ultima ubicacion conocida
    private Location mLastKnownLocation, mTempLocation;
    //identificadores para guardar la localizacion
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    //posicion por defecto
    private LatLng mDefaultLocation = new LatLng(13.730288, -89.719527);
    //marcador ubicacion - universidad
    private Marcador marcadorUNIVERSIDAD;
    private Marker marcadorMiUbicacion;
    //llave de DIrecion - diferente a la de mapa
    String keyDirection = " AIzaSyCvUUCQsb85wIUI3vaqvzstiO3_D4CiRjs ";
    Polyline ruta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        // encargado del mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //llamar al permiso de ubicacion
        permisoUbicacion();
    }

    private void permisoUbicacion() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //control zoom
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng coordenadas = new LatLng(13.730288, -89.719527);
        LatLng miUbicacion = new LatLng(13.713616, -89.719853);
        //titulo del marker
        mMap.addMarker(new MarkerOptions().position(coordenadas).title("USO"));
        //dibujando camino
        mMap.addPolyline(new PolylineOptions().add(coordenadas, miUbicacion).width(10).color(Color.BLUE));
        //zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenadas, DEFAULT_ZOOM));
        //para mostrar mi ubicacion actual
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    //resultado del permiso
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    //getDeviceLocation();//actualizo la ubicacion
                }
            }
        }
    }


  /*  private void getDeviceLocation() {
        if (mLocationPermissionGranted) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
               mLastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            }
            if(mLastKnownLocation==null){
                Toast.makeText(this, "GPS deshabilitado, porfavor activalo", Toast.LENGTH_SHORT).show();
            }
        }
        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mLastKnownLocation != null) {
            LatLng islamabad = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLng(islamabad));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(islamabad,DEFAULT_ZOOM));
            //agregando el marcador de la posicion actual
            marcadorMiUbicacion = mMap.addMarker(new MarkerOptions().position(islamabad).title("Ubicación Actual")
                    .icon((BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE))) );
        } else {
          //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
        }
    }*/

    //segundo creo un metodo para agregar un marcador en el mapa creo un obj LatLng para incluir latitud y longitud
    private void agregarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, -lng );

        //utilizamos el elemento CameraUpdate centrando la camara en la posicion de el marker
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(mDefaultLocation, 16);
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

  /*  //para la direccion
    public void pedirDireccion() {
        Toast.makeText(this,"Trazando ruta",Toast.LENGTH_SHORT).show();
        GoogleDirection.withServerKey(keyDirection)
                .from(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()))
                .to(new LatLng(marcadorUNIVERSIDAD.latitud,marcadorUNIVERSIDAD.longitud))
                .transportMode(TransportMode.DRIVING)
                .execute(this);
    }

    @Override
    public void onDirectionSuccess(Path.Direction direction, String rawBody) {
        Toast.makeText(this,"Ruta Trazada",Toast.LENGTH_SHORT).show();

        if (direction.isOK()) {
            if(ruta!=null) ruta.remove();
            Route route = direction.getRouteList().get(0);
            ArrayList<LatLng> directionPositionList = route.getLegList().get(0).getDirectionPoint();
            ruta = mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 3, Color.BLUE));
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Toast.makeText(this,"Fallo al trazar ruta",Toast.LENGTH_SHORT).show();
    }
*/


}