package com.example.llamadacthulhu.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText etNick;
    EditText etPassword;
    String nombreusu, contrasenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        etNick = (EditText)findViewById(R.id.etUser);
        etPassword = (EditText)findViewById(R.id.etContr);

        findViewById(R.id.btnLogin).setOnClickListener(this);

    }

    private void guardarnick(){
        nombreusu = etNick.getText().toString();
        SharedPreferences pref;
        pref = getSharedPreferences("pref",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("NombreUsuario",nombreusu);
        editor.commit();
    }

    private void loginUsuario(){

        nombreusu = etNick.getText().toString();
        contrasenia = etPassword.getText().toString();
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<Usuario> call = api.getUsuarioLogin(nombreusu, contrasenia);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    if(response.body().getContraseña().matches(contrasenia) && response.body().getNombre().matches(nombreusu)){
                        Intent intent = new Intent(login.this, postlogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "Credenciales inválidas.",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogin){
            guardarnick();
            loginUsuario();
        }
    }
}
