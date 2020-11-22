package com.example.llamadacthulhu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.llamadacthulhu.adapters.campaniaadapter;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Campania;
import com.example.llamadacthulhu.model.Personaje;
import com.perfilDesdePersonaje;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.util.Log;

import org.json.JSONObject;
import org.w3c.dom.Text;


public class fragment_aventuras extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String nombreCamp;
    ListView listView;
    List<String>listaNomUsus = new ArrayList<String>();
    List<String>listaNomPers = new ArrayList<String>();
    List<Personaje>listPers = new ArrayList<Personaje>();
    List<Campania> dataSet;
    private FragmentManager fm;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txtNomCamp;
    String nombreusu;
    List<Campania> lista;
    SharedPreferences pref;
    private static final String TAG ="FragmentAventuras";
    public fragment_aventuras() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_aventuras.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_aventuras newInstance(String param1, String param2) {
        fragment_aventuras fragment = new fragment_aventuras();
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
    Log.v(TAG,"Creando e inflando vista");

        return inflater.inflate(R.layout.fragment_aventuras,container,false);

    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        dataSet = new ArrayList<Campania>();
        listView = (ListView)getView().findViewById(R.id.listaavent);

         pref = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
         nombreusu = pref.getString("NombreUsuario","");
        //nombreusu = "Sergio"; //Hardcoded nombre usuario, hay que mirar que pasa con las preferencias.
        Log.v(TAG,"Realizando conexión");
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<List<Campania>> call = api.getCampaniasUsuario(nombreusu);

        call.enqueue(new Callback<List<Campania>>() {
            @Override
            public void onResponse(Call<List<Campania>> call, Response<List<Campania>> response) {
                lista = response.body();
                if(response.isSuccessful()){
                    Log.v(TAG,"Añado al dataSet y muestro la lista "+lista.get(0).getNombreCampania());
                    for(Campania c : response.body()) {
                        Log.v(TAG,"Añado las campañas "+c.getNombreCampania());
                        //c.setNombre(c.getNombre()); //hardcoded nombre, hay que mirar por qué no lee bien.
                        dataSet.add(c);
                    }
                    campaniaadapter adapter = new campaniaadapter(dataSet,getActivity().getApplicationContext());
                    listView.setAdapter(adapter);
                    listView.setClickable(true);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            txtNomCamp = view.findViewById(R.id.txtTitulo);
                            nombreCamp = txtNomCamp.getText().toString();
                            recogerPersonajesCampania(nombreCamp);


                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Campania>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });





    }
    private void recogerPersonajesCampania(String nombreCampania){
        Log.v(TAG,"Realizando conexión para recoger los personajes y mostrarlos");
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<List<Personaje>> call = api.getNombrePersonajesCampania(nombreCampania);
        call.enqueue(new Callback<List<Personaje>>() {
            @Override
            public void onResponse(Call<List<Personaje>> call, Response<List<Personaje>> response) {
                if(response.isSuccessful()){
                    Log.v(TAG,"Recojo los usuarios "+response.body());
                    for(Personaje p : response.body()){
                        listPers.add(p);
                    }
                    for(int i=0;i<listPers.size();i++){
                        listaNomPers.add(listPers.get(i).getNombre());
                        listaNomUsus.add(listPers.get(i).getUsuario());
                    }
                    Log.v(TAG,"Muestro la lista de nombre de personajes: "+listaNomPers);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("¿A que perfil quieres ir?");
                    builder.setMessage("Si quieres salir, tienes que usar el botón de back de tu móvil.");
                    if(listaNomPers.size() == 1){
                        builder.setPositiveButton(listaNomPers.get(0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NombreUsuario0",listaNomUsus.get(0));
                                editor.commit();
                                fm = getActivity().getSupportFragmentManager();
                                Fragment fragmentPerfilDesdePersonaje = new perfilDesdePersonaje();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPerfilDesdePersonaje).commit();

                            }
                        });

                    } else if(listaNomPers.size() == 2){
                        builder.setPositiveButton(listaNomPers.get(0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NombreUsuario0",listaNomUsus.get(0));
                                editor.commit();
                                fm = getActivity().getSupportFragmentManager();
                                Fragment fragmentPerfilDesdePersonaje = new perfilDesdePersonaje();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPerfilDesdePersonaje).commit();
                            }
                        });
                        builder.setNeutralButton(listaNomPers.get(1), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NombreUsuario0",listaNomUsus.get(1));
                                editor.commit();
                                fm = getActivity().getSupportFragmentManager();
                                Fragment fragmentPerfilDesdePersonaje = new perfilDesdePersonaje();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPerfilDesdePersonaje).commit();

                            }
                        });

                    }else if(listaNomPers.size() == 3) {
                        builder.setPositiveButton(listaNomPers.get(0), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NombreUsuario0",listaNomUsus.get(0));
                                editor.commit();
                                fm = getActivity().getSupportFragmentManager();
                                Fragment fragmentPerfilDesdePersonaje = new perfilDesdePersonaje();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPerfilDesdePersonaje).commit();
                            }
                        });
                        builder.setNeutralButton(listaNomPers.get(1), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NombreUsuario0",listaNomUsus.get(1));
                                editor.commit();
                                fm = getActivity().getSupportFragmentManager();
                                Fragment fragmentPerfilDesdePersonaje = new perfilDesdePersonaje();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPerfilDesdePersonaje).commit();
                            }
                        });
                        builder.setNegativeButton(listaNomPers.get(2), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("NombreUsuario0",listaNomUsus.get(2));
                                editor.commit();
                                fm = getActivity().getSupportFragmentManager();
                                Fragment fragmentPerfilDesdePersonaje = new perfilDesdePersonaje();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragmentPerfilDesdePersonaje).commit();
                            }
                        });
                    }
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }

            @Override
            public void onFailure(Call<List<Personaje>> call, Throwable t) {

            }
        });
    }

}
