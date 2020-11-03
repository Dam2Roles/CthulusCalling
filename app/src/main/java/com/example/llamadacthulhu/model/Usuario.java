package com.example.llamadacthulhu.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
    String nombre;
    String contrasenia;
    String email;
    Date fechaNacimiento;
    String sexo;
    String tipoPerfil;

    public Usuario(String nombre, String contraseña, String email, String sexo, String tipoPerfil) {
        this.nombre = nombre;
        this.contrasenia = contraseña;
        this.email = email;
        this.sexo = sexo;
        this.tipoPerfil = tipoPerfil;
    }

    public Usuario() {
    }

    public Usuario(JSONObject object){
        try {
            this.nombre = object.getString("nombre");
            this.contrasenia = object.getString("contrasenia");
            this.email = object.getString("email");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Usuario(String nombre, String contrasenia, String email) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
    }

    public static ArrayList<Usuario> fromJSON(JSONArray objects){
        ArrayList<Usuario> listausu = new ArrayList<Usuario>();
        for(int i = 0;i<objects.length();i++){
            try{
                listausu.add(new Usuario(objects.getJSONObject(i)));
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

        return listausu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contrasenia;
    }

    public void setContraseña(String contraseña) {
        this.contrasenia = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(String tipoPerfil) {
        this.tipoPerfil = tipoPerfil;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", contraseña='" + contrasenia + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", sexo='" + sexo + '\'' +
                ", tipoPerfil='" + tipoPerfil + '\'' +
                '}';
    }
}
