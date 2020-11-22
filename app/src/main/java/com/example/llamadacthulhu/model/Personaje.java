package com.example.llamadacthulhu.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Personaje {
    int idPersonaje;
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

    public Personaje(int idPersonaje, String nombre, String lugarNacimiento, String profesion, int edad, int fuerza, int constitucion, int tamanio, int destreza, int apariencia, int cordura, int inteligencia, int poder, int educacion, int idea, int suerte, int conocimiento) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.lugarNacimiento = lugarNacimiento;
        this.profesion = profesion;
        this.edad = edad;
        this.fuerza = fuerza;
        this.constitucion = constitucion;
        this.tamanio = tamanio;
        this.destreza = destreza;
        this.apariencia = apariencia;
        this.cordura = cordura;
        this.inteligencia = inteligencia;
        this.poder = poder;
        this.educacion = educacion;
        this.idea = idea;
        this.suerte = suerte;
        this.conocimiento = conocimiento;
    }

    public Personaje(JSONObject object){
        try {
            this.usuario = object.getString("usuario");
            this.campania= object.getString("campania");
            this.idPersonaje = object.getInt("idPersonaje");
            this.nombre = object.getString("nombre");
            this.lugarNacimiento = object.getString("lugarNacimiento");
            this.profesion = object.getString("profesion");
            this.edad = object.getInt("edad");
            this.fuerza = object.getInt("fuerza");
            this.constitucion = object.getInt("constitucion");
            this.tamanio = object.getInt("tamanio");
            this.destreza = object.getInt("destreza");
            this.apariencia = object.getInt("apariencia");
            this.cordura = object.getInt("cordura");
            this.inteligencia = object.getInt("inteligencia");
            this.poder = object.getInt("poder");
            this.educacion = object.getInt("educacion");
            this.idea = object.getInt("idea");
            this.suerte = object.getInt("suerte");
            this.conocimiento = object.getInt("conocimiento");

        }catch(JSONException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Personaje> fromJSON(JSONArray objects){
        ArrayList<Personaje> listapjs = new ArrayList<Personaje>();
        for (int i =0; i<objects.length();i++){
            try {
                listapjs.add(new Personaje(objects.getJSONObject(i)));
                            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return listapjs;
    }

    public Personaje() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getConstitucion() {
        return constitucion;
    }

    public void setConstitucion(int constitucion) {
        this.constitucion = constitucion;
    }

    public int getTamaño() {
        return tamanio;
    }

    public void setTamaño(int tamaño) {
        this.tamanio = tamaño;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getApariencia() {
        return apariencia;
    }

    public void setApariencia(int apariencia) {
        this.apariencia = apariencia;
    }

    public int getCordura() {
        return cordura;
    }

    public void setCordura(int cordura) {
        this.cordura = cordura;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getEducacion() {
        return educacion;
    }

    public void setEducacion(int educacion) {
        this.educacion = educacion;
    }

    public int getIdea() {
        return idea;
    }

    public void setIdea(int idea) {
        this.idea = idea;
    }

    public int getSuerte() {
        return suerte;
    }

    public void setSuerte(int suerte) {
        this.suerte = suerte;
    }

    public int getConocimiento() {
        return conocimiento;
    }

    public void setConocimiento(int conocimiento) {
        this.conocimiento = conocimiento;
    }

    public String getCampania() {
        return campania;
    }

    public void setCampania(String campania) {
        this.campania = campania;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "usuario='" + usuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", campania='" + campania + '\'' +
                ", lugarNacimiento='" + lugarNacimiento + '\'' +
                ", profesion='" + profesion + '\'' +
                ", edad=" + edad +
                ", fuerza=" + fuerza +
                ", constitucion=" + constitucion +
                ", tamaño=" + tamanio +
                ", destreza=" + destreza +
                ", apariencia=" + apariencia +
                ", cordura=" + cordura +
                ", inteligencia=" + inteligencia +
                ", poder=" + poder +
                ", educacion=" + educacion +
                ", idea=" + idea +
                ", suerte=" + suerte +
                ", conocimiento=" + conocimiento +
                '}';
    }
}



