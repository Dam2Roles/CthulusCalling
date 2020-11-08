package com.example.llamadacthulhu.api;

import com.example.llamadacthulhu.model.Campania;
import com.example.llamadacthulhu.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface InterfaceApi {

    @GET("usuarios/{nombre_usu}/{contrasenia}")
    Call<Usuario> getUsuarioLogin(@Path("nombre_usu")String nombreUsuario,
                                  @Path("contrasenia")String contrasenia);

   @POST("usuarios")
    Call<Usuario>crearUsuario(@Body Usuario usuario);

   @GET("usuarios/{nombre_usu}")
    Call<Usuario>recibirNombreImagen(@Body Usuario usuario);

    @GET("campania/dameCampanias/{nombre_usu}" )
    Call<List<Campania>>getCampaniasUsuario(@Path("nombre_usu")String nombreUsuario);

}
