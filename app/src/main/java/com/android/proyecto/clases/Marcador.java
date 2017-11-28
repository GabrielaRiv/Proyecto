package com.android.proyecto.clases;

import java.io.Serializable;

/**
 * Created by Gabriela on 26/11/2017.
 */

public class Marcador implements Serializable {
    public double latitud;
    public double longitud;
    public String titulo;

    public Marcador(double latitud, double longitud, String titulo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.titulo = titulo;
    }
}
