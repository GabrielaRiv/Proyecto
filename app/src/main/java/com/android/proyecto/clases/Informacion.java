package com.android.proyecto.clases;

/**
 * Created by Yova on 11/10/2017.
 */

public class Informacion {
    private int Iduniversidad;
    private String Nombre, Descripcion, Vision, Carreras, Telefono, Direccion, Web, latitud, longitud;

    public Informacion(int Iduniversidad, String Nombre, String Descripcion, String Vision, String Carreras,
    String Telefono, String Direccion, String Web, String latitud, String longitud){
        this.Vision = Vision;
        this.Descripcion = Descripcion;
        this.Nombre = Nombre;
        this.Iduniversidad = Iduniversidad;
        this.Carreras = Carreras;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.Web = Web;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIduniversidad(){return Iduniversidad;}

    public String getNombre(){return Nombre;}

    public String getDescripcion(){return Descripcion;}

    public String getVision() {return Vision;}

    public String getCarreras() { return Carreras; }

    public String getTelefono() {return Telefono;}

    public String getDireccion() {return Direccion; }

    public String getWeb() {return Web; }

    public String getLatitud() {return latitud;}

    public String getLongitud() {return longitud;}
}
