package com.example.llamadacthulhu.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.llamadacthulhu.R;

public class profile extends AppCompatActivity {
    TextView etNick;
    TextView numCamp;
    TextView numpj;
    Button btnVolver;
    ImageView imgPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        etNick = (TextView) findViewById(R.id.TxtNick);
        numCamp = (TextView) findViewById(R.id.txtNumAvent);
        numpj = (TextView) findViewById(R.id.txtnumPjs);
        btnVolver = (Button) findViewById(R.id.btnVolver);
        imgPerfil = (ImageView) findViewById(R.id.imagenPerfil);


    }
}
