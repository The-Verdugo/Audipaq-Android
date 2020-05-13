package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class detalle extends AppCompatActivity {
    TextView Nombre,empresa;
    String Empresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Datos datos=(Datos)getIntent().getExtras().getSerializable("Objeto");
        Nombre=(TextView)findViewById(R.id.txvdetalleNombre);
        empresa=(TextView)findViewById(R.id.txvdetalleEmpresa);
        Nombre.setText(datos.getNombre());
        Empresa("http://192.168.1.75/consulta_empresa.php?nombre="+datos.getNombre());
    }

    public void Empresa(String Url){
            JsonArrayRequest request = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    JSONObject jsonObject = null;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            jsonObject = response.getJSONObject(i);
                            empresa.setText(jsonObject.getString("nombre_empresa"));
                        } catch (JSONException e) {
                            Toast.makeText(detalle.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(detalle.this, "Ocurrio un error al recuperar los datos", Toast.LENGTH_SHORT).show();
                }
            });
        RequestQueue requestQueue = Volley.newRequestQueue(detalle.this);
        requestQueue.add(request);
    }
}
