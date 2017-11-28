package com.android.proyecto.clases;

import java.util.ArrayList;

/**
 * Created by Gabriela on 27/11/2017.
 */

public class Lista {

        ArrayList<Marcador> lstMarcadores;

        private void llenarMarcadores(){
        lstMarcadores       = new ArrayList<>();
        lstMarcadores.add(new Marcador(13.730287, -89.719526999999971,"Universidad de Sonsonate"));
        lstMarcadores.add(new Marcador(13.7161024, -89.20346899999998,"Universidad Nacional"));
        lstMarcadores.add(new Marcador(13.7159035, -89.1536987,"Universidad Don Bosco"));
    }

}
