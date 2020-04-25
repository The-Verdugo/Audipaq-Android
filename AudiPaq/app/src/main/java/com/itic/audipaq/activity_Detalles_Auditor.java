package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class activity_Detalles_Auditor extends AppCompatActivity
{
    TextView nombreAuditor,nombre_empresa,fecha_registro,rfc,curp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detalles__auditor);

        nombreAuditor=(TextView) findViewById(R.id.nombreAuditor);
        nombre_empresa=(TextView) findViewById(R.id.nombre_empresa);
        fecha_registro=(TextView) findViewById(R.id.fecha_registro);
        rfc=(TextView) findViewById(R.id.rfc);
        curp=(TextView) findViewById(R.id.curp);
    }
}
