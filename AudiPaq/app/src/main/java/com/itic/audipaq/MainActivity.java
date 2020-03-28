package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText usuario,password;
    Button btnIniciar,btnrecuperarpass;
    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario=(EditText)findViewById(R.id.txtusuario);
        password=(EditText)findViewById(R.id.txtpassword);
        btnIniciar=(Button)findViewById(R.id.btnIniciar);
        btnrecuperarpass=(Button)findViewById(R.id.btnrecuperarpass);
        recuperarpreferencias();
        SharedPreferences preferences= getSharedPreferences("preferenciaslogin",Context.MODE_PRIVATE);
        boolean sesion= preferences.getBoolean("sesion",false);
        if (sesion){
            Intent intent = new Intent(MainActivity.this,Home_Auditor.class);
            startActivity(intent);
            finish();
        }
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user=usuario.getText().toString();
                pass=password.getText().toString();
                if (!user.isEmpty()&& !pass.isEmpty()) {
                    //Falta agregar URL del archivo php para iniciar sesion
                    IniciarSesion("http://192.168.1.75/validar_usuario.php");
                }else{
                    Toast.makeText(MainActivity.this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void IniciarSesion(String Url){
        StringRequest request= new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.isEmpty()){
                    guardarPreferencias();
                    Intent intent = new Intent(MainActivity.this,Home_Auditor.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Ocurrio un error al intentar iniciar sesión", Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    private void guardarPreferencias(){
        SharedPreferences preferences= getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("usuario",user);
        editor.putString("password",pass);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    private void recuperarpreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciaslogin",Context.MODE_PRIVATE);
        usuario.setText(preferences.getString("usuario",""));
        password.setText(preferences.getString("password",""));
    }
}
