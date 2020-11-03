package com.example.llamadacthulhu.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.llamadacthulhu.R;

public class MainActivity extends AppCompatActivity {
    Button btnRegistro;
    Button btnLogin;
    Button btnInvitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        btnLogin = (Button) findViewById(R.id.btnInicio);
        btnInvitado = (Button) findViewById(R.id.btnInvitado);
    }

    public void onRegistro(View w) {
        Intent iReg = new Intent(this, registro.class);
        startActivity(iReg);
    }

    public void onIniciarSesion(View v) {
        Intent iLog = new Intent(this, login.class);
        startActivity(iLog);
    }

    public void onInvitado(View v) {
        Intent iGuest = new Intent(this, postlogin.class);
        startActivity(iGuest);
    }
}
