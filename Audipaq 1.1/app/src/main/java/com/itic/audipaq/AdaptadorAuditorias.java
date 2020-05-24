package com.itic.audipaq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdaptadorAuditorias extends BaseAdapter {
    Context context;
    List<DatosAuditorias> listaObjetos;

    public AdaptadorAuditorias(Context context, List<DatosAuditorias> listaObjetos) {
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
        vista=inflater.inflate(R.layout.elemento_auditoria,null);
        TextView nombre =(TextView) vista.findViewById(R.id.txtNombreacta);
        TextView NoActa =(TextView) vista.findViewById(R.id.txtnoacta);
        TextView Fecha =(TextView) vista.findViewById(R.id.txtfechaActa);
        TextView Estatus =(TextView) vista.findViewById(R.id.txtEstadoActa);

        nombre.setText("Auditria a: "+listaObjetos.get(i).getArea());
        NoActa.setText(listaObjetos.get(i).getId_persona());
        Fecha.setText(listaObjetos.get(i).getFechainicio());
        Estatus.setText(listaObjetos.get(i).getEstatus());

        return vista;
    }
}
