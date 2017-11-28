package com.android.proyecto.clases;

import java.util.ArrayList;

/**
 * Created by Gabriela on 27/11/2017.
 */

public class MarcadoresSingleton {

        //TODO clase singleton que permite tener una sola instancia de la lista marcadores
        //TODO me permite ver los marcadores en todas las activitys
        private static MarcadoresSingleton instance = null;
        protected MarcadoresSingleton() {
                // Exists only to defeat instantiation.
        }
        public static MarcadoresSingleton getInstance() {
                if(instance == null) {
                        instance = new MarcadoresSingleton();
                }
                return instance;
        }

        public ArrayList<Marcador> lstMarcadores;

        public void llenarMarcadores(){
                lstMarcadores       = new ArrayList<>();
                //TODO modificar longitud y latitud
                lstMarcadores.add(new Marcador(13.730287, -89.719526999999971,"UCA"));
                lstMarcadores.add(new Marcador(13.7161024, -89.20346899999998,"Universidad Francisco Gavidia"));
                lstMarcadores.add(new Marcador(13.730287, -89.719526999999971,"ITCA"));
                lstMarcadores.add(new Marcador(13.7159035, -89.1536987,"UMA"));
                lstMarcadores.add(new Marcador(13.730287, -89.719526999999971,"UNAB"));
                lstMarcadores.add(new Marcador(13.7159035, -89.1536987,"UNASA"));
                lstMarcadores.add(new Marcador(13.7161024, -89.20346899999998,"UPAM"));
                lstMarcadores.add(new Marcador(13.7159035, -89.1536987,"USO"));
        }

        public Marcador getMarcador(String nombre){
                for (Marcador m:lstMarcadores) {
                        if(m.titulo.contains(nombre)) return m;
                }
                return null;
        }

}
