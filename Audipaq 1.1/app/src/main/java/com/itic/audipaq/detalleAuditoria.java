package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.io.Serializable;
import java.util.ArrayList;

public class detalleAuditoria extends AppCompatActivity {
    ListView listadeEventos;
    ArrayList<DatosEventos> Lista;
    private TextView textView;
    RequestQueue requestQueue;
    private int id;
    int rol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_auditoria);
        listadeEventos=(ListView) findViewById(R.id.listEventos);
        textView = (TextView) findViewById(R.id.textViewdetalleAuditoria);
        DatosAuditorias datos=(DatosAuditorias) getIntent().getExtras().getSerializable("Objeto");
        textView.setText("Auditoria a: "+datos.getArea());
        id=Integer.parseInt(datos.getId_persona());
        Lista=new ArrayList<DatosEventos>();
        Lista=consulta("http://192.168.1.75/consulta_evento.php?id="+id);
        AdaptadorEventos miadaptador =new AdaptadorEventos(detalleAuditoria.this,Lista);
        listadeEventos.setAdapter(miadaptador);
        listadeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DatosAuditorias datos = (DatosAuditorias) adapterView.getItemAtPosition(i);
                Intent intent= new Intent(detalleAuditoria.this,detalleAuditoria.class);
                intent.putExtra("Objeto",(Serializable) datos);
                startActivity(intent);
            }
        });

    }
    public void agregarelementos(int id,String id_detalle,String fecha,String area,String prioridad,String auditor){
        Lista.add(new DatosEventos(id,id_detalle,fecha,area,prioridad,auditor));
        AdaptadorEventos miadaptador=new AdaptadorEventos(detalleAuditoria.this,Lista);
        listadeEventos.setAdapter(miadaptador);
    }
    public ArrayList consulta(String Url){
        JsonArrayRequest request = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        agregarelementos(jsonObject.getInt("id_detalle"),jsonObject.getString("comentarios"),jsonObject.getString("fecha"),jsonObject.getString("fk_id_area"),jsonObject.getString("tipo_prioridad"),jsonObject.getString("nombre_persona"));
                    } catch (JSONException e) {
                        Toast.makeText(detalleAuditoria.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(detalleAuditoria.this, "No tienes registos de eventos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(detalleAuditoria.this);
        requestQueue.add(request);
        return Lista;
    }
}

