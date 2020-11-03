package com.example.llamadacthulhu.adapters;

import android.content.Context;
import android.text.Layout;
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

public class pjadapter extends ArrayAdapter<Personaje> {
    private ArrayList<Personaje> dataSet;
    Context mContext;
    private int lastPosition = -1;

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

    public pjadapter(ArrayList<Personaje> data, Context context){
        super(context, R.layout.itemlistapj, data);
        this.dataSet = data;
        this.mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent ){

        Personaje pj = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.itemlistapj, parent, false);


            result=convertView;
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        return convertView;
    }
}
