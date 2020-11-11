package com.example.llamadacthulhu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.llamadacthulhu.adapters.campaniaadapter;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Campania;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_aventuras#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_aventuras extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ListView lista;
    ArrayList<Campania> listaCamp;


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
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_aventuras, container, false);
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        lista = (ListView)getView().findViewById(R.id.listaavent);

        SharedPreferences preferences = this.getActivity().getSharedPreferences("DatosAUsar", Context.MODE_PRIVATE);
        String nombreusu = preferences.getString("NombreUsuario","No hay información");

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceApi api = retrofit.create(InterfaceApi.class);
        Call<List<Campania>> call = api.getCampaniasUsuario(nombreusu);

        call.enqueue(new Callback<List<Campania>>() {
            @Override
            public void onResponse(Call<List<Campania>> call, Response<List<Campania>> response) {
                if(response.isSuccessful()){
                    for(Campania c : response.body()) {
                        listaCamp.add(c);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Campania>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });



        campaniaadapter adapter = new campaniaadapter(listaCamp,getActivity().getApplicationContext());
        lista.setAdapter(adapter);

    }
}