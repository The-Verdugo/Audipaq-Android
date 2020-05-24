package com.itic.audipaq;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import java.util.ArrayList;


public class AuditoriasAdminFragment extends Fragment {
    ListView listadeauditorias;
    ArrayList<DatosAuditorias> Lista;
    private TextView textView;
    RequestQueue requestQueue;
    private int id;
    int rol;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_auditorias,container,false);
        listadeauditorias=(ListView)v.findViewById(R.id.listaAuditorias);
        recuperarpreferencias();
        textView=(TextView)v.findViewById(R.id.textViewAuditores);
        Lista=new ArrayList<DatosAuditorias>();
        Lista=consulta("http://192.168.1.75/consulta_acta.php?id="+id);
        AdaptadorAuditorias miadaptador=new AdaptadorAuditorias(getActivity(),Lista);
        listadeauditorias.setAdapter(miadaptador);
        listadeauditorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DatosAuditorias datos = (DatosAuditorias) adapterView.getItemAtPosition(i);
                Intent intent= new Intent(getContext(),detalle.class);
                intent.putExtra("Objeto",(Serializable) datos);
                startActivity(intent);
            }
        });
        return v;
    }
    public void agregarelementos(int id,String id_persona,String fechainicio,String status,String departamento,String area){
        Lista.add(new DatosAuditorias(id,id_persona,fechainicio,status,"",departamento,area));
        AdaptadorAuditorias miadaptador=new AdaptadorAuditorias(getActivity(),Lista);
        listadeauditorias.setAdapter(miadaptador);
    }
    public ArrayList consulta(String Url){
        JsonArrayRequest request = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        agregarelementos(jsonObject.getInt("id_acta"),jsonObject.getString("id_acta"),jsonObject.getString("fecha_inicio"),jsonObject.getString("tipo_status"),jsonObject.getString("nombre_departamento"),jsonObject.getString("nombre_area"));
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
        SharedPreferences preferences = getContext().getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
        rol=preferences.getInt("rol",0);
        id=preferences.getInt("id_persona",0);
    }
}
