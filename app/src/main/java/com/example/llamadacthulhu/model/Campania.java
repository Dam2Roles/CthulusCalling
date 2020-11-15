package com.example.llamadacthulhu.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Campania {
    String nombreCampania;
    String creador;
    String descripcion;

    public Campania(String nombreCampania, String creador, String descripcion) {
        this.nombreCampania = nombreCampania;
        this.creador = creador;
        this.descripcion = descripcion;
    }

    public Campania() {
    }
    public Campania(JSONObject object){
        try {
            this.nombreCampania = object.getString("nombreCampania");
            this.creador = object.getString("creador");
            this.descripcion = object.getString("descripcion");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Campania> fromJSON(JSONArray objects){
        ArrayList<Campania> listacamps = new ArrayList<Campania>();
        for (int i =0; i<objects.length();i++){
            try {
                listacamps.add(new Campania(objects.getJSONObject(i)));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return listacamps;
    }

    public String getNombreCampania() {
        return nombreCampania;
    }

    public void setNombreCampania(String nombreCampania) {
        this.nombreCampania = nombreCampania;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Campania{" +
                "nombreCampania='" + nombreCampania + '\'' +
                ", creador='" + creador + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
