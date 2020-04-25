package com.itic.audipaq;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_lista_auditores,container,false);
        listadeentrenamientos=(ListView)v.findViewById(R.id.listAuditores);
        textView=(TextView)v.findViewById(R.id.textViewAuditores);
        Lista=new ArrayList<Datos>();

        Lista.add(new Datos(1,"Ramon Alfredo Diaz Espinoza",R.drawable.ic_happy_face,"IT consult"));
        Lista.add(new Datos(2,"Roberto Gomez Alvarado",R.drawable.ic_happy_face,"AudIT"));
        Lista.add(new Datos(3,"Ricardo Alvarez Lopez",R.drawable.ic_happy_face,"ContPaq"));
        Lista.add(new Datos(4,"Elber Verdin Santana",R.drawable.ic_happy_face,"IT consult"));
        Lista.add(new Datos(5,"Jorge Gonzalez Mendoza",R.drawable.ic_happy_face,"AudIT"));
        Lista.add(new Datos(6,"Ricardo Guzman Alvarez",R.drawable.ic_happy_face,"ContPaq"));
        Lista.add(new Datos(7,"Guadalupe Perez Diaz",R.drawable.ic_happy_face,"IT consult"));
        Lista.add(new Datos(8,"Alexis Prado Rodiguez",R.drawable.ic_happy_face,"AudIT"));
        Lista.add(new Datos(9,"Arturo Lopez Guzman",R.drawable.ic_happy_face,"ContPaq"));
        Adaptador miadaptador=new Adaptador(getContext(),Lista);
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
}
