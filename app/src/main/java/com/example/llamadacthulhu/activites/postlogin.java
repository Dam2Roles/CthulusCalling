package com.example.llamadacthulhu.activites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.llamadacthulhu.InicioFragment;
import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.fragment_aventuras;
import com.example.llamadacthulhu.ui.gallery.AventurasFragment;
import com.example.llamadacthulhu.ui.home.ProfileFragment;
import com.example.llamadacthulhu.ui.slideshow.PersonajesFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Retrofit;

public class postlogin extends AppCompatActivity  {

    private AppBarConfiguration mAppBarConfiguration;
    private FragmentManager fm;
    private TextView welcomeuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlogin3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //SharedPreferences misPreferencias = getSharedPreferences("DatosAUsar", Context.MODE_PRIVATE);
        //String nusuario = misPreferencias.getString("NombreUsuario", "nombreusu");
        //welcomeuser = findViewById(R.id.welcomeuser);
        //welcomeuser.setText("Bienvenido "+nusuario);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.btnInicio, R.id.btnAventuras, R.id.btnPersonajes)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.postlogin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.inicio:

                fm = getSupportFragmentManager();
                Fragment fragmentI = new InicioFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentI).commit();

                return true;
            case R.id.perfil:

                fm = getSupportFragmentManager();
                Fragment fragmentP = new ProfileFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentP).commit();
                return true;
            case R.id.aventuras:

                fm = getSupportFragmentManager();
                Fragment fragmentA = new fragment_aventuras();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentA).commit();
                return true;
            case R.id.personajes:

                fm = getSupportFragmentManager();
                Fragment fragmentPj = new PersonajesFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPj).commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getImagenRetrofit(View view){
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
    }

}



