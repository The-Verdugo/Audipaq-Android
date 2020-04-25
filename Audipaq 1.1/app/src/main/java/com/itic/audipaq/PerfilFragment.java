package com.itic.audipaq;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class PerfilFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Button cerrar;
        View v=inflater.inflate(R.layout.fragment_perfil,container,false);
        cerrar=(Button)v.findViewById(R.id.btnCerrarsesion);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences= getActivity().getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(getActivity(),login.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
