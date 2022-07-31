package com.nabin.retrofitdemo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MyApi {
    /*
        --- Get Methods ---
        Get method is the process or method to retrive the data form the server from the given url;
     */

    // Default Get method returns all results
    @GET("posts")
    Call<List<ModelClass>> getPosts();

    //Get method with static custom parameters
    @GET("posts")
    Call<List<ModelClass>> getPosts(
            @Query("userId") int userId,
            @Query("id") Integer id,
            @Query("_sort") String sortBy,
            @Query("_order") String orderBy
    );

    //Get Method with dynamic parameters
    @GET("posts")
    Call<List<ModelClass>> getPosts(@QueryMap Map<String, String> parameters);

    @GET("{path}")
    Call<List<ModelClass>> getPostWithCustomPath(@Path("path") String strPath);

    /*
    --- Post Methods ---
    POST is a request method to send data from HTTP Client to the http server.
     */

    //Post method passing model class object
    @POST("posts")
    Call<ModelClass> createPost(@Body ModelClass model);

    //Post method passing individual values (static)
    @FormUrlEncoded
    @POST("posts")
    Call<ModelClass> createPost(@Field("userId") int userId,
                                @Field("title") String title,
                                @Field("body") String body);

    //Post method passing individual values (dynamic)
    @FormUrlEncoded
    @POST("posts")
    Call<ModelClass> createPost(@FieldMap Map<String, String> values);

    /*
    ---- PUT Method ---
    PUT is the request to change or create the data
     */

    @PUT("posts/{id}")
    Call<ModelClass> putPost(@Path("id") int id,@Body ModelClass model);

    /*
    ---- PATCH Method ---
    PATCH is the request to change the partial data
     */

    @PATCH("posts/{id}")
    Call<ModelClass> patchPost(@Path("id") int id,@Body ModelClass model);

    /*
    ---- DELETE Method ---
    DELETE is the request to delete the data
     */

    @DELETE("path/{id}")
    Call<ModelClass> deletePost(@Path("id") int id);

}
