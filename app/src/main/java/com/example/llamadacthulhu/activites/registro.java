package com.example.llamadacthulhu.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Usuario;

import java.time.LocalDate;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class registro extends AppCompatActivity implements View.OnClickListener {
    EditText txtNick;
    EditText txtEmail;
    EditText password;
    EditText conPassword;
    String nombreusu;
    String contrasenia;
    String confirmacontra;
    String email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();
        txtNick = (EditText) findViewById(R.id.txtNickname);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtCont);
        conPassword = (EditText) findViewById(R.id.txtConfCont);

        findViewById(R.id.btnRegistro).setOnClickListener(this);


    }

    private void registroUsuario(){
        nombreusu=txtNick.getText().toString().trim();
        contrasenia=password.getText().toString().trim();
        email=txtEmail.getText().toString().trim();
        confirmacontra=conPassword.getText().toString().trim();

        Usuario usuario = new Usuario(nombreusu,contrasenia,email);

        if(email.isEmpty()){
            txtEmail.setError("Debes introducir un email");
            txtEmail.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtEmail.setError("Debes introducir un formato válido de email");
            txtEmail.requestFocus();
            return;
        }
        if(contrasenia.isEmpty()){
            password.setError("Debes introducir una contraseña");
            password.requestFocus();
        }
        if(!contrasenia.matches(confirmacontra)){
            conPassword.setError("Las contraseñas deben coincidir");
            conPassword.requestFocus();
        }
        if(contrasenia.length()<5){
            password.setError("La contraseña debe tener mínimo 5 caracteres");
            password.requestFocus();
        }
        if(nombreusu.isEmpty()){
            txtNick.setError("Debes introducir un nombre de usuario");
            txtNick.requestFocus();
        }



        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<Usuario> call = api.crearUsuario(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
               if(!response.isSuccessful()){
                   Toast.makeText(getApplicationContext(),  response.message(),Toast.LENGTH_LONG).show();
               }else {
                   Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_LONG).show();
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
        if (v.getId() == R.id.btnRegistro){
            registroUsuario();
        }
    }
}
