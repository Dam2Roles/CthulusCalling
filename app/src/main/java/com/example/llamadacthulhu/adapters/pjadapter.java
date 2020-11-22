package com.example.llamadacthulhu.adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.model.Personaje;

import java.util.ArrayList;
import java.util.List;

public class pjadapter extends ArrayAdapter<Personaje> {
    private List<Personaje> dataSet;
    Context mContext;
    private int lastPosition = -1;

    private static final String TAG ="AdapterPersonajes";

    //interfaz
    TextView txtNom;
    TextView txtUsr;
    TextView txtCamp;
    TextView txtLugNac;
    TextView txtProf;
    TextView txtEDAD;
    TextView txtFUE;
    TextView txtCON;
    TextView txtTAM;
    TextView txtDES;
    TextView txtAPA;
    TextView txtCOR;
    TextView txtINT;
    TextView txtPOD;
    TextView txtEDU;
    TextView txtIDE;
    TextView txtSUE;
    TextView txtCONN;



    //Datos de un personaje
    String usuario;
    String nombre;
    String campania;
    String lugarNacimiento;
    String profesion;
    int edad;
    int fuerza;
    int constitucion;
    int tamanio;
    int destreza;
    int apariencia;
    int cordura;
    int inteligencia;
    int poder;
    int educacion;
    int idea;
    int suerte;
    int conocimiento;

    public static class ViewHolder {
        TextView txtNom;
        TextView txtUsr;
        TextView txtCamp;
        TextView txtLugNac;
        TextView txtProf;
        TextView txtEDAD;
        TextView txtFUE;
        TextView txtCON;
        TextView txtTAM;
        TextView txtDES;
        TextView txtAPA;
        TextView txtCOR;
        TextView txtINT;
        TextView txtPOD;
        TextView txtEDU;
        TextView txtIDE;
        TextView txtSUE;
        TextView txtCONN;
    }

    public pjadapter(List<Personaje> data, Context context){
        super(context, R.layout.itemlistapj, data);
        this.dataSet = data;
        this.mContext = context;
        lastPosition =data.size();
    }

    public View getView(int position, View convertView, ViewGroup parent ){

        Personaje pj = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.itemlistapj, parent, false);

            Log.v(TAG,"Cogiendo el personaje "+pj);

            usuario = pj.getUsuario();
            nombre = pj.getNombre();
            campania = pj.getCampania();
            lugarNacimiento = pj.getLugarNacimiento();
            profesion = pj.getProfesion();
            edad = pj.getEdad();
            fuerza = pj.getFuerza();
            constitucion = pj.getConstitucion();
            tamanio = pj.getTamanio();
            destreza = pj.getDestreza();
            apariencia = pj.getApariencia();
            cordura = pj.getCordura();
            inteligencia = pj.getInteligencia();
            poder = pj.getPoder();
            educacion = pj.getEducacion();
            idea = pj.getIdea();
            suerte = pj.getSuerte();
            conocimiento = pj.getConocimiento();

            //cargamos la interfaz del itemlistapj.xml
            txtNom = convertView.findViewById(R.id.txtNombrepj);
            txtUsr = convertView.findViewById(R.id.txtUser);
            txtCamp = convertView.findViewById(R.id.txtCamp);
            txtLugNac = convertView.findViewById(R.id.txtLugNac);
            txtProf = convertView.findViewById(R.id.txtProf);
            txtEDAD = convertView.findViewById(R.id.txtEDAD);
            txtFUE = convertView.findViewById(R.id.txtFUE);
            txtCON = convertView.findViewById(R.id.txtCON);
            txtTAM = convertView.findViewById(R.id.txtTAM);
            txtDES = convertView.findViewById(R.id.txtDES);
            txtAPA = convertView.findViewById(R.id.txtAPA);
            txtCOR = convertView.findViewById(R.id.txtCOR);
            txtINT = convertView.findViewById(R.id.txtINT);
            txtPOD = convertView.findViewById(R.id.txtPOD);
            txtEDU = convertView.findViewById(R.id.txtEDU);
            txtIDE = convertView.findViewById(R.id.txtIDE);
            txtSUE = convertView.findViewById(R.id.txtSUE);
            txtCONN = convertView.findViewById(R.id.txtCONN);

            //Seteamos los valores en los campos de texto


            txtNom.setText(nombre);
            txtUsr.setText(usuario);
            txtCamp.setText(campania);
            txtLugNac.setText(lugarNacimiento);
            txtProf.setText(profesion);
            txtEDAD.setText(String.valueOf(edad));
            txtFUE.setText(String.valueOf(fuerza));
            txtCON.setText(String.valueOf(constitucion));
            txtTAM.setText(String.valueOf(tamanio));
            txtDES.setText(String.valueOf(destreza));
            txtAPA.setText(String.valueOf(apariencia));
            txtCOR.setText(String.valueOf(cordura));
            txtINT.setText(String.valueOf(inteligencia));
            txtPOD.setText(String.valueOf(poder));
            txtEDU.setText(String.valueOf(educacion));
            txtIDE.setText(String.valueOf(idea));
            txtSUE.setText(String.valueOf(suerte));
            txtCONN.setText(String.valueOf(conocimiento));


            result=convertView;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        //lastPosition = position;

        return convertView;
    }
}
