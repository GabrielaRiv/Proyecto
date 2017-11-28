package com.android.proyecto.universidades;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.proyecto.Departamentos;
import com.android.proyecto.Perfil;
import com.android.proyecto.R;
import com.android.proyecto.clases.Informacion;
import com.android.proyecto.clases.RequestHandler;
import com.android.proyecto.clases.ShareInformacion;
import com.android.proyecto.clases.URLs;
import com.android.proyecto.paginas.uma;
import com.android.proyecto.paginas.unab;
import com.android.proyecto.paginas.uso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Sonsonate extends AppCompatActivity {
    LinearLayout uso;
    LinearLayout uma;
    LinearLayout unab;
    private Toolbar toolbar;

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
        setContentView(R.layout.activity_sonsonate);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        uso = (LinearLayout) findViewById(R.id.uso);
        uma = (LinearLayout) findViewById(R.id.uma);
        unab = (LinearLayout) findViewById(R.id.unab);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         uso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion1();
            }
        });

        uma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion22();
            }
        });

        unab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion33();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent intent = new Intent(Sonsonate.this, Perfil.class);
        startActivity(intent);
        return true;
    }

    private void verinformacion33(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor8 = 8;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String q) {
                super.onPostExecute(q);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj8 = new JSONObject(q);

                    //if no error in response
                    if (!obj8.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj8.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject belloJson = obj8.getJSONObject("bello");

                        Informacion bello = new Informacion(
                                belloJson.getInt("Iduniversidad"),
                                belloJson.getString("Nombre"),
                                belloJson.getString("Descripcion"),
                                belloJson.getString("Vision"),
                                belloJson.getString("Carreras"),
                                belloJson.getString("Telefono"),
                                belloJson.getString("Direccion"),
                                belloJson.getString("Web"),
                                belloJson.getString("latitud"),
                                belloJson.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(bello);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), unab.class));
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
                String valorunab = String.valueOf(valor8);
                params.put("Iduniversidad", valorunab);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_BELLO, params);
            }
        }
        verInformacion unab = new verInformacion();
        unab.execute();
    }


    private void verinformacion22(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor7 = 7;

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
                    JSONObject obj7 = new JSONObject(u);

                    //if no error in response
                    if (!obj7.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj7.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject catolicaJson = obj7.getJSONObject("uma");

                        Informacion uma = new Informacion(
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
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(uma);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), uma.class));
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
                String valoruma = String.valueOf(valor7);
                params.put("Iduniversidad", valoruma);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_UMA, params);
            }
        }
        verInformacion uma = new verInformacion();
        uma.execute();
    }


    private void verinformacion1(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor6 = 6;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String w) {
                super.onPostExecute(w);
                progressBar.setVisibility(View.GONE);


                try {
                    //converting response to json object
                    JSONObject obj6 = new JSONObject(w);

                    //if no error in response
                    if (!obj6.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj6.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject usoJson = obj6.getJSONObject("uso");

                        Informacion uso = new Informacion(
                                usoJson.getInt("Iduniversidad"),
                                usoJson.getString("Nombre"),
                                usoJson.getString("Descripcion"),
                                usoJson.getString("Vision"),
                                usoJson.getString("Carreras"),
                                usoJson.getString("Telefono"),
                                usoJson.getString("Direccion"),
                                usoJson.getString("Web"),
                                usoJson.getString("latitud"),
                                usoJson.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(uso);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), uso.class));
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
                String valoruso = String.valueOf(valor6);
                params.put("Iduniversidad", valoruso);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_USO, params);
            }
        }
        verInformacion us = new verInformacion();
        us.execute();
    }
}
