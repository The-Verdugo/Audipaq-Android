package com.itic.audipaq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HomeAdminFragment extends Fragment {
    ListView listadeentrenamientos;
    ArrayList<Datos> Lista;
    private TextView textView;
    RequestQueue requestQueue;
    int rol;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_lista_auditores,container,false);
        listadeentrenamientos=(ListView)v.findViewById(R.id.listAuditores);
        recuperarpreferencias();
        textView=(TextView)v.findViewById(R.id.textViewAuditores);
        Lista=new ArrayList<Datos>();
        Lista=roles("http://192.168.1.75/consulta.php?tipo="+rol);
        Adaptador miadaptador=new Adaptador(getActivity(),Lista);
        listadeentrenamientos.setAdapter(miadaptador);
        listadeentrenamientos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Datos datos = (Datos) adapterView.getItemAtPosition(i);
                Intent intent= new Intent(getContext(),detalle.class);
                intent.putExtra("Objeto",(Serializable) datos);
                startActivity(intent);
            }
        });
        return v;
    }
    public void agregarelementos(int id,String Nombre){
        Lista.add(new Datos(id,Nombre,R.drawable.ic_happy_face,"IT consult"));
        Adaptador miadaptador=new Adaptador(getActivity(),Lista);
        listadeentrenamientos.setAdapter(miadaptador);
    }
    public ArrayList roles(String Url){
        JsonArrayRequest request = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        agregarelementos(jsonObject.getInt("id_persona"),jsonObject.getString("nombre_persona")+" "+jsonObject.getString("apellido_paterno")+" "+jsonObject.getString("apellido_materno"));
                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Ocurrio un error al recuperar los datos", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        return Lista;
    }
    private void recuperarpreferencias(){
        SharedPreferences preferences = getActivity().getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        rol=preferences.getInt("rol",0);
    }
}
