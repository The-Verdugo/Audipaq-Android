package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText usuario,password;
    Button btnIniciar,btnrecuperarpass;
    String user, pass,nombre;
    int rol,id_persona;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario=(EditText)findViewById(R.id.txtemail);
        password=(EditText)findViewById(R.id.txtpassword);
        btnIniciar=(Button)findViewById(R.id.btniniciar);
        recuperarpreferencias();
        SharedPreferences preferences= getSharedPreferences("preferenciaslogin",Context.MODE_PRIVATE);
        boolean sesion= preferences.getBoolean("sesion",false);
        if (sesion){
            switch (rol){
                case 1:
                    Intent intent2 = new Intent(login.this,MainActivity_Auditor.class);
                    startActivity(intent2);
                    finish();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    Intent intent = new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
            }
        }
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=usuario.getText().toString();
                pass=password.getText().toString();
                if (!user.isEmpty()&& !pass.isEmpty()) {
                    //Falta agregar URL del archivo php para iniciar sesion
                    rol=roles("http://192.168.1.75/consultar.php?user="+user+"&pass="+pass);
                    IniciarSesion("http://192.168.1.75/validar_usuario.php",rol,id_persona);
                }else{
                    Toast.makeText(login.this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void asignarid(int id){
        id_persona=id;
    }
    public int roles(String Url){
        JsonArrayRequest request = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        asignarid(Integer.parseInt(jsonObject.getString("id_persona")));
                        rol = Integer.parseInt(jsonObject.getString("fk_id_tipo"));
                        nombre= jsonObject.getString("nombre_persona");
                    } catch (JSONException e) {
                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Ocurrio un error al recuperar los datos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        return rol;
    }
    public void IniciarSesion(final String Url, final int userrol,final int id){
        StringRequest request= new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                            switch (userrol){
                                case 1:
                                    guardarPreferencias(userrol,nombre,id_persona);
                                    Intent intent1 = new Intent(login.this,MainActivity_Auditor.class);
                                    startActivity(intent1);
                                    finish();
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    guardarPreferencias(userrol,nombre, id_persona);
                                    Intent intent4 = new Intent(login.this,MainActivity.class);
                                    startActivity(intent4);
                                    finish();
                                    break;
                                default:
                            }
                }else{
                    Toast.makeText(login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, "Ocurrio un error al intentar iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> Datos= new HashMap<String, String>();
                Datos.put("usuario",user);
                Datos.put("password",pass);
                return Datos;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    private void guardarPreferencias(int rol,String nombre, int id){
        SharedPreferences preferences= getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("usuario",user);
        editor.putString("password",pass);
        editor.putBoolean("sesion",true);
        editor.putInt("rol",rol);
        editor.putString("nombre",nombre);
        editor.putInt("id_persona",id);
        editor.commit();
    }
    private void recuperarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciaslogin",Context.MODE_PRIVATE);
        usuario.setText(preferences.getString("usuario",""));
        password.setText(preferences.getString("password",""));
        rol=preferences.getInt("rol",0);
    }
}
