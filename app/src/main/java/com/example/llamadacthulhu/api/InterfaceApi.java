package com.example.llamadacthulhu.api;

import com.example.llamadacthulhu.model.Campania;
import com.example.llamadacthulhu.model.Personaje;
import com.example.llamadacthulhu.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface InterfaceApi {

    @GET("usuarios/{nombre_usu}/{contrasenia}")
    Call<Usuario> getUsuarioLogin(@Path("nombre_usu")String nombreUsuario,
                                  @Path("contrasenia")String contrasenia);

   @POST("usuarios")
    Call<Usuario>crearUsuario(@Body Usuario usuario);

   @GET("usuarios/{nombre_usu}")
   Call<Usuario>recibirNombreImagen(@Body Usuario usuario);

   @GET("usuarios/obtenerUsuario/{nombre_usu}")
   Call<Usuario>getInfoUsuario(@Path("nombre_usu")String nombreUsuario);

   @GET("campania/dameCampanias/{nombre_usu}" )
   Call<List<Campania>>getCampaniasUsuario(@Path("nombre_usu")String nombreUsuario);

   @GET("usuarios/damePersonajes/{nombre_usuario}")
   Call<List<Personaje>>getPersonajesUsuario(@Path("nombre_usuario")String nombreUsuario);

   @PUT("usuarios/actualizarUsuario")
    Call<Usuario>actualizarInfoUsuario(@Body Usuario usuario);

   @GET("campania/damePersonajesCampania/{nombre_camp}")
    Call<List<Personaje>>getNombrePersonajesCampania(@Path("nombre_camp")String nombreCampania);

}
