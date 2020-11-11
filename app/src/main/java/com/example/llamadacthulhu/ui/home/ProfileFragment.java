package com.example.llamadacthulhu.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.util.Date;

public class ProfileFragment extends Fragment {

    private View root;
    Button btnVolver2;
    Spinner dropdown;
    Spinner dropdownSexo;
    TextView txtEmail;
    TextView txtFec;
    TextView txtnick;
    String nombreUsuario;
    String sexo;
    String email;
    Date fecNac;
    String tipoUsuario;
    ImageView imgPerfil;
    byte[] arrayBiteImg;
    Bitmap bmpImg;





    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);
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

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<Usuario>call = api.getInfoUsuario(pref.getString("NombreUsuario","Sergio"));

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    sexo = response.body().getSexo();
                    email = response.body().getEmail();
                    fecNac = response.body().getFechaNacimiento();
                    tipoUsuario = response.body().getTipoPerfil();
                    txtEmail.setText(email);
                    txtFec.setText(fecNac.toString());
                    txtnick.setText(nombreUsuario);
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

    }

