package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class activity_ver_Auditorias extends AppCompatActivity
{
    Button btnBuscar;
    SearchView svBuscar;
    ListView listviewAuditorias;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__auditorias);

        btnBuscar=(Button) findViewById(R.id.btnBuscar);
        svBuscar=(SearchView) findViewById(R.id.svBuscar);
        listviewAuditorias=(ListView) findViewById(R.id.listviewAuditorias);
    }
}
