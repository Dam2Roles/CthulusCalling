package com.example.llamadacthulhu.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.llamadacthulhu.R;
import com.example.llamadacthulhu.api.InterfaceApi;
import com.example.llamadacthulhu.api.RetrofitClientInstance;
import com.example.llamadacthulhu.model.Campania;
import com.example.llamadacthulhu.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class campaniaadapter extends ArrayAdapter<Campania> {

    private List<Campania> dataSet;
    Context mContext;
    private int lastPosition = -1;
    TextView txtNombre;
    TextView txtUsu;
    TextView txtDesc;
    String creador;
    String titulo;
    String desc;

    private static final String TAG ="AdapterAventuras";

    public static class ViewHolder {
        TextView txtNombre;
        TextView txtUsu;
        TextView txtDesc;

    }

    public campaniaadapter(List<Campania> data, Context context){
        super(context, R.layout.itemlistaaventura, data);
        this.dataSet = data;
        this.mContext = context;
        lastPosition =data.size() ;
    }

    public View getView(int position, View ConvertView, ViewGroup parent){
        //lastPosition++;
        Campania camp = getItem(position);//Coge elemento 0 y los va cargando hasta el tope.
        ViewHolder viewHolder;
        Log.v(TAG,"Cogiendo el item "+camp);
        final View result;

        if(ConvertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ConvertView = inflater.inflate(R.layout.itemlistaaventura,parent, false);
            //coger elementos layout para inflar datos.
            creador = camp.getCreador();
            desc = camp.getDescripcion();
            titulo = camp.getNombreCampania();

            txtNombre=ConvertView.findViewById(R.id.txtTitulo);
            txtUsu=ConvertView.findViewById(R.id.txtCreador);
            txtDesc=ConvertView.findViewById(R.id.txtDesc);

            txtNombre.setText(titulo);
            txtUsu.setText(creador);
            txtDesc.setText(desc);



            result=ConvertView;

            ConvertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) ConvertView.getTag();
            result=ConvertView;
        }
        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        //lastPosition = position;

        return ConvertView;
    }

}
