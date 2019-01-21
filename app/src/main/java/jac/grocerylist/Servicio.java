package jac.grocerylist;


import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.GET;

import retrofit2.Call;
import retrofit2.http.Path;
import jac.grocerylist.Items;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.GET;

import retrofit2.Call;
import retrofit2.http.Path;


public interface Servicio {



    @GET("getItems2.php")
    Call<Items> getItems();

    @FormUrlEncoded
    @POST("addItem.php")
    Call<Result> addItem(
            @Field("product") String product,
            @Field("branch") String branch,
            @Field("presentation") String presentation
         //   @Field("presentationCount") double presentationCount,
          //  @Field("price") double price
            );

    @FormUrlEncoded
    @POST("editItem.php")
    Call<Result> editItem(
            @Field("product") String product,
            @Field("branch") String branch,
            @Field("presentation") String presentation,
            @Field("idProducto") int idProducto
    );
    @FormUrlEncoded
    @POST("deleteItem.php")
    Call<Result> deleteItem(
            @Field("idProducto") int idProducto
    );



/*

    @FormUrlEncoded
    @POST("registroConfiguracion.php")
    Call<Result> registroConfiguracion(
            @Field("ip") String ip,
            @Field("celular") String celular);


    @FormUrlEncoded
    @POST("actualizarPedido.php/{idPedido}")
    Call<Result> actualizarPedido(
            @Path("idPedido") int idPedido);



    @FormUrlEncoded
    @POST("obtenerProductos.php/{idPedido}")
    Call<Productos> getProductos(@Field("idPedido") int idPedido);


    @FormUrlEncoded
    @POST("actualizarProducto.php/{idProducto}")
    Call<Result> actualizarProducto(
            @Field("idProducto") int idProducto);

    @FormUrlEncoded
    @POST("obtenerProductos.php/{idPedido}")
    Call<Spinners> getProductos2(@Field("idPedido") int idPedido);


    @GET("obtenerMotivosCancelacion.php")
    Call<Spinners> obtenerMotivosCancelacion();

*/



}
