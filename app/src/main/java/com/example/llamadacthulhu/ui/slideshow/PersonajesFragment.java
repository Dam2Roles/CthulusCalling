package com.example.llamadacthulhu.ui.slideshow;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.adapters.campaniaadapter;
import com.example.llamadacthulhu.adapters.pjadapter;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Campania;
import com.example.llamadacthulhu.model.Personaje;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PersonajesFragment extends Fragment {

    private PersonajesViewModel slideshowViewModel;
    ListView listView;
    ArrayList<Personaje> dataSet;
    String nombreusu;
    private static final String TAG ="PersonajesFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_personajes,container,false);
    }
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        dataSet = new ArrayList<Personaje>();
        listView = (ListView)getView().findViewById(R.id.listaPers);
        SharedPreferences preferences = this.getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        nombreusu = preferences.getString("NombreUsuario","");
        Log.v(TAG,"Realizando conexión");
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<List<Personaje>> call = api.getPersonajesUsuario(nombreusu);

        call.enqueue(new Callback<List<Personaje>>() {
            @Override
            public void onResponse(Call<List<Personaje>> call, Response<List<Personaje>> response) {
                Log.v(TAG,"No devuelvo nada");
                if(response.isSuccessful()){
                    Log.v(TAG,"Añado al dataSet y muestro la lista");
                    for(Personaje p : response.body()) {
                        Log.v(TAG,"Añado los personajes "+p);
                        p.setNombre("Las máscaras de Nyaratoleph"); //hardcoded nombre, hay que mirar por qué no lee bien.
                        dataSet.add(p);
                    }
                    pjadapter adapter = new pjadapter(dataSet,getActivity().getApplicationContext());
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Personaje>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
