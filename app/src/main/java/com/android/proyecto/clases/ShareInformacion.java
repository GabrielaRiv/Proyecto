package com.android.proyecto.clases;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by Yova on 02/10/2017.
 */

public class ShareInformacion {
    //the constants
    private static final String SHARED_PREF_NAME = "aplicacion";
    private static final String KEY_NOMBRE = "clavenombre";
    private static final String KEY_DESCRIPCION = "clavedescripcion";
    private static final String KEY_IDU = "claveid";
    private static final String KEY_VISION = "Vision";
    private static final String KEY_CARRERA = "Carrera";
    private static final String KEY_TELEFONO = "Telefono";
    private static final String KEY_DIRECCION = "Direccion";
    private static final String KEY_WEB = "WEB";
    private static final String KEY_LATITUD = "latitud";
    private static final String KEY_LONGITUD = "longitud";

    private static ShareInformacion mInstance;
    private static Context mCtx;

    private ShareInformacion(Context context) {
        mCtx = context;
    }

    public static synchronized ShareInformacion getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ShareInformacion(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void verInformacion(Informacion info) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_IDU, info.getIduniversidad());
        editor.putString(KEY_NOMBRE, info.getNombre());
        editor.putString(KEY_DESCRIPCION, info.getDescripcion());
        editor.putString(KEY_VISION, info.getVision());
        editor.putString(KEY_CARRERA, info.getCarreras());
        editor.putString(KEY_TELEFONO, info.getTelefono());
        editor.putString(KEY_DIRECCION, info.getDireccion());
        editor.putString(KEY_WEB, info.getWeb());
        editor.putString(KEY_LATITUD, info.getLatitud());
        editor.putString(KEY_LONGITUD, info.getLongitud());
        editor.apply();
    }

    public Informacion getInformacion() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Informacion(
                sharedPreferences.getInt(KEY_IDU, -1),
                sharedPreferences.getString(KEY_NOMBRE, null),
                sharedPreferences.getString(KEY_DESCRIPCION, null),
                sharedPreferences.getString(KEY_VISION, null),
                sharedPreferences.getString(KEY_CARRERA, null),
                sharedPreferences.getString(KEY_TELEFONO, null),
                sharedPreferences.getString(KEY_DIRECCION, null),
                sharedPreferences.getString(KEY_WEB,null),
                sharedPreferences.getString(KEY_LONGITUD,null),
                sharedPreferences.getString(KEY_LATITUD,null)
                );
    }
}
