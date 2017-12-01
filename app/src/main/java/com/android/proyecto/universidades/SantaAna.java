package com.android.proyecto.universidades;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.proyecto.Departamentos;
import com.android.proyecto.Instrucciones;
import com.android.proyecto.Perfil;
import com.android.proyecto.R;
import com.android.proyecto.clases.Informacion;
import com.android.proyecto.clases.RequestHandler;
import com.android.proyecto.clases.ShareInformacion;
import com.android.proyecto.clases.URLs;
import com.android.proyecto.paginas.catolica;
import com.android.proyecto.paginas.gavidia;
import com.android.proyecto.paginas.itca;
import com.android.proyecto.paginas.unasa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
public class SantaAna extends AppCompatActivity {
    LinearLayout itca;
    LinearLayout catolica ;
    LinearLayout gavidia;
    LinearLayout autonoma;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == event.KEYCODE_BACK) {
            Intent atras = new Intent(this, Departamentos.class);
            startActivity(atras);
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_santa_ana);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        itca     = (LinearLayout) findViewById(R.id.itca);
        catolica = (LinearLayout) findViewById(R.id.catolica);
        gavidia  = (LinearLayout) findViewById(R.id.gavidia);
        autonoma = (LinearLayout) findViewById(R.id.autonoma);

        itca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion();
            }
        });

        catolica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion3();
            }
        });

        gavidia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion2();
            }
        });

        autonoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion4();
            }
        });

    }

    private void verinformacion4(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor4 = 5;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String m) {
                super.onPostExecute(m);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj4 = new JSONObject(m);

                    //if no error in response
                    if (!obj4.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj4.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject unasaJson = obj4.getJSONObject("unasa");

                        Informacion unasa = new Informacion(
                                unasaJson.getInt("Iduniversidad"),
                                unasaJson.getString("Nombre"),
                                unasaJson.getString("Descripcion"),
                                unasaJson.getString("Vision"),
                                unasaJson.getString("Carreras"),
                                unasaJson.getString("Telefono"),
                                unasaJson.getString("Direccion"),
                                unasaJson.getString("Web"),
                                unasaJson.getString("latitud"),
                                unasaJson.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(unasa);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), unasa.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                String valorunasa = String.valueOf(valor4);
                params.put("Iduniversidad", valorunasa);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_UNASA, params);
            }
        }
        verInformacion una = new verInformacion();
        una.execute();
    }


    private void verinformacion3(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor2 = 4;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String o) {
                super.onPostExecute(o);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj4 = new JSONObject(o);

                    //if no error in response
                    if (!obj4.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj4.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject gavidiaJson = obj4.getJSONObject("gavidia");

                        Informacion gavidia = new Informacion(
                                gavidiaJson.getInt("Iduniversidad"),
                                gavidiaJson.getString("Nombre"),
                                gavidiaJson.getString("Descripcion"),
                                gavidiaJson.getString("Vision"),
                                gavidiaJson.getString("Carreras"),
                                gavidiaJson.getString("Telefono"),
                                gavidiaJson.getString("Direccion"),
                                gavidiaJson.getString("Web"),
                                gavidiaJson.getString("latitud"),
                                gavidiaJson.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(gavidia);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), gavidia.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                String valorgavidia = String.valueOf(valor2);
                params.put("Iduniversidad", valorgavidia);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_GAVIDIA, params);
            }
        }
        verInformacion ga = new verInformacion();
        ga.execute();
    }


    private void verinformacion2(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor1 = 3;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String u) {
                super.onPostExecute(u);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj3 = new JSONObject(u);

                    //if no error in response
                    if (!obj3.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj3.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject catolicaJson = obj3.getJSONObject("catolica");

                        Informacion catolica = new Informacion(
                                catolicaJson.getInt("Iduniversidad"),
                                catolicaJson.getString("Nombre"),
                                catolicaJson.getString("Descripcion"),
                                catolicaJson.getString("Vision"),
                                catolicaJson.getString("Carreras"),
                                catolicaJson.getString("Telefono"),
                                catolicaJson.getString("Direccion"),
                                catolicaJson.getString("Web"),
                                catolicaJson.getString("latitud"),
                                catolicaJson.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(catolica);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), catolica.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                String valorcatolica = String.valueOf(valor1);
                params.put("Iduniversidad", valorcatolica);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_CATOLICA, params);
            }
        }
        verInformacion ca = new verInformacion();
        ca.execute();
    }


    private void verinformacion(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor = 2;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String k) {
                super.onPostExecute(k);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj2 = new JSONObject(k);

                    //if no error in response
                    if (!obj2.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj2.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj2.getJSONObject("itca");

                        Informacion itca = new Informacion(
                                userJson.getInt("Iduniversidad"),
                                userJson.getString("Nombre"),
                                userJson.getString("Descripcion"),
                                userJson.getString("Vision"),
                                userJson.getString("Carreras"),
                                userJson.getString("Telefono"),
                                userJson.getString("Direccion"),
                                userJson.getString("Web"),
                                userJson.getString("latitud"),
                                userJson.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(itca);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), itca.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                String valoritca = String.valueOf(valor);
                params.put("Iduniversidad", valoritca);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_ITCA, params);
            }
        }
        verInformacion ud = new verInformacion();
        ud.execute();
    }

    //para el menu
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.perfil:
                Intent intent1 = new Intent(SantaAna.this, Perfil.class);
                startActivity(intent1);
                break;

            case R.id.acerca:
                Intent intent2 = new Intent(SantaAna.this, Acercade.class);
                startActivity(intent2);
                break;

            case R.id.infor:
                Intent intent3 = new Intent(SantaAna.this, Instrucciones.class);
                startActivity(intent3);
        }
        return super.onOptionsItemSelected(item);
    }
}


