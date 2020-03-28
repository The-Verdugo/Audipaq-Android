package com.itic.audipaq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home_Auditor extends AppCompatActivity {
    Button btncerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__auditor);
        btncerrar=(Button)findViewById(R.id.btncerrarSesion);
        btncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences= getSharedPreferences("preferenciaslogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();

                Intent intent = new Intent(Home_Auditor.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
