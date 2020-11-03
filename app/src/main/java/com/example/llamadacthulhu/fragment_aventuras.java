package com.example.llamadacthulhu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.llamadacthulhu.adapters.campaniaadapter;
import com.example.llamadacthulhu.model.Campania;

import java.util.ArrayList;


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
        Campania camp1 = new Campania("Agua de coco","Es monster blanco", "Buenos días");
        listaCamp.add(camp1);
        View root = inflater.inflate(R.layout.itemlistaaventura, container, true);
        lista=(ListView)root.findViewById(R.id.listaavent);
        campaniaadapter adapter = new campaniaadapter(listaCamp, lista.getContext() );
        lista.setAdapter(adapter);

        return root;
    }
}