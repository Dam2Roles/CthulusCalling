package com;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Usuario;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link perfilDesdePersonaje#newInstance} factory method to
 * create an instance of this fragment.
 */
public class perfilDesdePersonaje extends Fragment {


    //Datos interfaz
    private View root;
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
    private static final String TAG ="ProfileFragmentDesdePersonaje";








    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public perfilDesdePersonaje() {
        // Required empty public constructor
    }


    public static perfilDesdePersonaje newInstance(String param1, String param2) {
        perfilDesdePersonaje fragment = new perfilDesdePersonaje();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_perfil_desde_personaje, container, false);
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
        return root;
    }

    private void recogerInfoUsuario(){
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref",Context.MODE_PRIVATE);
        nombreUsuario = pref.getString("NombreUsuario0","Default");
        Log.v(TAG,"Inicio conexión");
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<Usuario> call = api.getInfoUsuario(pref.getString("NombreUsuario0",""));
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
}