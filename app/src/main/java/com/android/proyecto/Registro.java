package com.android.proyecto;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.proyecto.clases.RequestHandler;
import com.android.proyecto.clases.SharedPrefManager;
import com.android.proyecto.clases.URLs;
import com.android.proyecto.clases.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

        EditText editTextUsername, editTextEmail, editTextPassword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro);


            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            //if the user is already logged in we will directly start the profile activity
            if (SharedPrefManager.getInstance(this).isLoggedIn()) {
                finish();
                startActivity(new Intent(this, Perfil.class));
                return;
            }

            editTextUsername = (EditText) findViewById(R.id.editTextUsername);
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if user pressed on button register
                    //here we will register the user to server
                    registerUser();
                }
            });

            findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //if user pressed on login
                    //we will open the login screen
                    finish();
                    startActivity(new Intent(Registro.this, Login.class));
                }
            });

        }

        private void registerUser() {
            final String username = editTextUsername.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();

            //first we will do the validations

            if (TextUtils.isEmpty(username)) {
                editTextUsername.setError("Ingrese Usuario");
                editTextUsername.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(email)) {
                editTextEmail.setError("Ingrese Correo");
                editTextEmail.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Ingrese un correo valido");
                editTextEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                editTextPassword.setError("Ingrese Contraseña");
                editTextPassword.requestFocus();
                return;
            }

            //if it passes all the validations

            class RegisterUser extends AsyncTask<Void, Void, String> {

                private ProgressBar progressBar;

                @Override
                protected String doInBackground(Void... voids) {
                    //creating request handler object
                    RequestHandler requestHandler = new RequestHandler();

                    //creating request parameters
                    HashMap<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("email", email);
                    params.put("password", password);

                    //returing the response
                    return requestHandler.sendPostRequest(URLs.URL_REGISTER, params);
                }

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    //displaying the progress bar while user registers on the server
                    progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    progressBar.setVisibility(View.VISIBLE);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    //hiding the progressbar after completion
                    progressBar.setVisibility(View.GONE);

                    try {
                        //converting response to json object
                        JSONObject obj = new JSONObject(s);

                        //if no error in response
                        if (!obj.getBoolean("error")) {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject("user");

                            //creating a new user object
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("username"),
                                    userJson.getString("email")
                            );

                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                            //starting the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Perfil.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            //executing the async task
            RegisterUser ru = new RegisterUser();
            ru.execute();
        }

    }
