package com.itic.audipaq;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorEventos extends BaseAdapter {
    Context context;
    List<DatosEventos> listaObjetos;

    public AdaptadorEventos(Context context, List<DatosEventos> listaObjetos) {
        this.context = context;
        this.listaObjetos = listaObjetos;
    }

    @Override
    public int getCount() {
        return listaObjetos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaObjetos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listaObjetos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista= view;
        LayoutInflater inflater=LayoutInflater.from(context);
        vista=inflater.inflate(R.layout.elemento_evento,null);
        TextView Nombre = (TextView) vista.findViewById(R.id.txvNombreEvento);
        TextView Fecha = (TextView) vista.findViewById(R.id.txvfechaEvento);
        TextView Auditado = (TextView) vista.findViewById(R.id.txvAuditadoEvento);
        TextView Prioridad = (TextView) vista.findViewById(R.id.txvPrioridadEvento);

        Nombre.setText(listaObjetos.get(i).getId_detalle());
        Fecha.setText(listaObjetos.get(i).getFecha());
        Auditado.setText((listaObjetos.get(i).getAuditor()));
        Prioridad.setText(listaObjetos.get(i).getPrioridad());
        switch (listaObjetos.get(i).getPrioridad()){
            case "Alta":
                Prioridad.setTextColor(Color.parseColor("#F31313"));
                break;
            case "Mediana":
                Prioridad.setTextColor(Color.parseColor("#FF9800"));
                break;
            case "Baja":
                Prioridad.setTextColor(Color.parseColor("#8BC34A"));
                break;
        }

        return vista;
    }
}
