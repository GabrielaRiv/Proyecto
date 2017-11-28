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
                lstMarcadores.add(new Marcador(13.990198, -89.559823,"UCA"));
                lstMarcadores.add(new Marcador(13.992571, -89.565372,"Universidad Francisco Gavidia"));
                lstMarcadores.add(new Marcador(13.974301, -89.569290,"ITCA"));
                lstMarcadores.add(new Marcador(13.722327, -89.728830,"UMA"));
                lstMarcadores.add(new Marcador(13.721250, -89.721928,"UNAB"));
                lstMarcadores.add(new Marcador(13.976491, -89.588709,"UNASA"));
                lstMarcadores.add(new Marcador(13.919699, -89.847063,"UPAM"));
                lstMarcadores.add(new Marcador(13.730288, -89.719527,"USO"));
        }

        public Marcador getMarcador(String nombre){
                for (Marcador m:lstMarcadores) {
                        if(m.titulo.contains(nombre)) return m;
                }
                return null;
        }

}
