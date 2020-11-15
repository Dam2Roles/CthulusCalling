package com.example.llamadacthulhu.ui.gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.adapters.campaniaadapter;
import com.example.llamadacthulhu.model.Campania;

import java.util.ArrayList;
import java.util.List;

public class AventurasFragment extends Fragment {

    ListView lista;
    ArrayList<Campania> listaCamp;
    private static final String TAG ="AventurasFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listaCamp = new ArrayList<Campania>();
        View root = inflater.inflate(R.layout.fragment_aventuras, container, false);
        Log.v(TAG,"Inflando vista mal");
        return root;
    }
}
