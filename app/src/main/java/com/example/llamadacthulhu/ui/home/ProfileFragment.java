package com.example.llamadacthulhu.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.llamadacthulhu.R;

public class ProfileFragment extends Fragment {

    private View root;
    Button btnVolver2;
    Spinner dropdown;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_profile, container, false);
        btnVolver2 = root.findViewById(R.id.btnVolver2);
        dropdown = root.findViewById(R.id.spinnerTipo);
        String[] itemsSpinner = new String[]{"Privado", "público"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemsSpinner);
        dropdown.setAdapter(adapterSpinner);

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

    }

