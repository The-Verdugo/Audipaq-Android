package com.itic.audipaq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class Adaptador extends BaseAdapter {
    Context context;
    List<Datos>listaObjetos;

    public Adaptador(Context context, List<Datos> listaObjetos) {
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
        vista=inflater.inflate(R.layout.elemento_auditor,null);
        ImageView Imagen=(ImageView)vista.findViewById(R.id.img_auditor);
        TextView textoNombre=(TextView)vista.findViewById(R.id.txvnomAuditor);
        TextView empresa=(TextView)vista.findViewById(R.id.txvEmpresa);

        empresa.setText(listaObjetos.get(i).getEmpresa());
        textoNombre.setText(listaObjetos.get(i).getNombre()+" "+listaObjetos.get(i).getApellidos());
        Imagen.setImageResource(listaObjetos.get(i).getImagen());
        return vista;
    }
}
