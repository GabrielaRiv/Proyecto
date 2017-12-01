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
import com.android.proyecto.Perfil;
import com.android.proyecto.R;
import com.android.proyecto.clases.Informacion;
import com.android.proyecto.clases.RequestHandler;
import com.android.proyecto.clases.ShareInformacion;
import com.android.proyecto.clases.URLs;
import com.android.proyecto.paginas.upam;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
public class Ahuachapan extends AppCompatActivity {
    LinearLayout Panamericana;
   // private Toolbar toolbar;

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
        setContentView(R.layout.activity_ahuchapan);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

       // toolbar = (Toolbar) findViewById(R.id.toolbar);
        Panamericana = (LinearLayout) findViewById(R.id.Panamericana);

        Panamericana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verinformacion();
            }
        });

       // setSupportActionBar(toolbar);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent intent = new Intent(Ahuachapan.this, Perfil.class);
        startActivity(intent);
        return true;
    }

    private void verinformacion(){

        class verInformacion extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;
            private int valor9 = 1;

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
                    JSONObject obj1 = new JSONObject(k);

                    //if no error in response
                    if (!obj1.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj1.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject user1Json = obj1.getJSONObject("upam");

                        Informacion upam = new Informacion(
                                user1Json.getInt("Iduniversidad"),
                                user1Json.getString("Nombre"),
                                user1Json.getString("Descripcion"),
                                user1Json.getString("Vision"),
                                user1Json.getString("Carreras"),
                                user1Json.getString("Telefono"),
                                user1Json.getString("Direccion"),
                                user1Json.getString("Web"),
                                user1Json.getString("latitud"),
                                user1Json.getString("longitud")
                        );

                        //storing the user in shared preferences
                        ShareInformacion.getInstance(getApplicationContext()).verInformacion(upam);

                        //starting the profile activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), upam.class));
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
                String valorupam = String.valueOf(valor9);
                params.put("Iduniversidad", valorupam);

                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_UPAM, params);
            }
        }
        verInformacion ud = new verInformacion();
        ud.execute();
    }
}


