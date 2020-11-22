package com.example.llamadacthulhu.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.activites.login;
import com.example.llamadacthulhu.activites.postlogin;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Usuario;
import com.google.gson.Gson;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.sql.Date;


public class ProfileFragment extends Fragment {

    //Datos interfaz
    private View root;
    Button btnVolver2;
    Button btnModificar;
    Spinner dropdown;
    Spinner dropdownSexo;
    TextView txtEmail;
    TextView txtFec;
    TextView txtnick;

    //Datos para la interfaz
    String nombreUsuario;
    String sexo;
    String email;
    Date fecNac;
    String cont;
    String tipoUsuario;
    ImageView imgPerfil;
    byte[] arrayBiteImg;
    Bitmap bmpImg;

    //Datos de modificar el usuario
    Usuario usuarioMod = new Usuario();
    String tipoUsuMod;
    String sexoMod;
    Date fechaMod;
    String correoMod;
    String contMod;

    private static final String TAG ="ProfileFragment";




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);
        btnModificar = root.findViewById(R.id.btnActualizar);
        btnVolver2 = root.findViewById(R.id.btnVolver2);
        dropdown = root.findViewById(R.id.spinnerTipo);
        dropdownSexo = root.findViewById(R.id.spinnerSexo);
        txtEmail = root.findViewById(R.id.txtEm);
        txtFec = root.findViewById(R.id.txtNaci);
        txtnick = root.findViewById(R.id.TxtNick);
        imgPerfil = root.findViewById(R.id.imagenPerfil);
        String[] itemsSpinnerSexo = new String[]{"H", "M"};
        String[] itemsSpinner = new String[]{"privado", "público"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsSpinner);
        dropdown.setAdapter(adapterSpinner);
        ArrayAdapter<String> adapterSpinnerSexo = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsSpinnerSexo);
        dropdownSexo.setAdapter(adapterSpinnerSexo);
        recogerInfoUsuario();
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarInfoUsuario();
            }
        });

        btnVolver2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context contexto = getContext();
                int duracion = Toast.LENGTH_LONG;
                Toast toastSalida = Toast.makeText(contexto, "Hasta la próxima...", duracion);
                toastSalida.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // acciones que se ejecutan tras los milisegundos
                        System.exit(0);
                    }
                }, 3000);


            }
        });

        return root;
    }
    private void recogerInfoUsuario(){
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
        nombreUsuario = pref.getString("NombreUsuario","Default");
        Log.v(TAG,"Inicio conexión");
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<Usuario>call = api.getInfoUsuario(pref.getString("NombreUsuario","Sergio"));
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.v(TAG,"No devuelvo nada");
                if(response.isSuccessful()){
                    Log.v(TAG,"Devuelvo los datos");
                    sexo = response.body().getSexo();
                    email = response.body().getEmail();
                    fecNac = new Date(response.body().getFechaNacimiento().getTime());
                    tipoUsuario = response.body().getTipoPerfil();
                    cont = response.body().getContraseña();
                    txtEmail.setText(email);
                    txtFec.setText(fecNac.toString());
                    txtnick.setText(nombreUsuario);
                    ArrayAdapter myAdapterTipoPerfil = (ArrayAdapter)dropdown.getAdapter();
                    ArrayAdapter myAdapterSexo = (ArrayAdapter)dropdownSexo.getAdapter();
                    int spinnerPositionTipoPerfil = myAdapterTipoPerfil.getPosition(tipoUsuario);
                    int spinnerPosotionSexo = myAdapterSexo.getPosition(sexo);
                    dropdown.setSelection(spinnerPositionTipoPerfil);
                    dropdownSexo.setSelection(spinnerPosotionSexo);
                    arrayBiteImg = response.body().getImagen();
                    bmpImg = BitmapFactory.decodeByteArray(arrayBiteImg,0,arrayBiteImg.length);
                    imgPerfil.setImageBitmap(Bitmap.createScaledBitmap(bmpImg,imgPerfil.getWidth(),imgPerfil.getHeight(),false));
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }
    private void actualizarInfoUsuario(){
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
        nombreUsuario = pref.getString("NombreUsuario","Default");
        tipoUsuMod = dropdown.getSelectedItem().toString();
        sexoMod = dropdownSexo.getSelectedItem().toString();
/*        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.id.imagenPerfil);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArrayImg = stream.toByteArray();
        fechaMod = (Date) txtFec.getText();
        correoMod = txtEmail.getText().toString();
        contMod = cont;*/
        usuarioMod.setNombre(nombreUsuario);
        usuarioMod.setTipoPerfil(tipoUsuMod);
        usuarioMod.setSexo(sexoMod);
        Log.v(TAG,"Iniciando conexión de actualización");
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<Usuario>call = api.actualizarInfoUsuario(usuarioMod);
        Log.v(TAG,"Envio el usuario "+usuarioMod);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Log.v(TAG, response.message());
                if(response.isSuccessful()){
                    Log.v(TAG,"Envío los datos del usuario "+usuarioMod);
                    Toast.makeText(getActivity(),"Perfil actualizado correctamente",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(),"Error al actualizar",Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getActivity(),"Perfil actualizado correctamente",Toast.LENGTH_LONG).show();
            }
        });
    }

    }

