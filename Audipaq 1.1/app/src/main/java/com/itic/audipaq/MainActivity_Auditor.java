package com.itic.audipaq;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_Auditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_auditor);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        BottomNavigationView bottomMenu=findViewById(R.id.Bottom_navigation_aud);
        bottomMenu.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_aud,new HomeAuditorFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment =null;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment=new HomeAuditorFragment();
                    break;
                case R.id.nav_auditorias:
                    selectedFragment=new AuditoriasAdminFragment();
                    break;
                case R.id.nav_perfil:
                    selectedFragment=new PerfilFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_aud,selectedFragment).commit();
            return true;
        }
    };
}
