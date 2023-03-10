package com.cavanosa.crudretrofitapp.interfaces;

import com.cavanosa.crudretrofitapp.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface
{
    @GET("example_select.php")
    Call<Person> getNameHobby(
            @Query("name") String name,
            @Query("hobby") String hobby
    );

    @GET("example_select.php")
    Call<List<Person>> getNameHobby();

    @FormUrlEncoded
    @POST("example_insert.php")
    Call<Person> insertPerson(
            @Field("name") String name,
            @Field("hobby") String hobby
    );

    @FormUrlEncoded
    @POST("example_update.php")
    Call<Person> updatePerson(
            @Field("id") int id,
            @Field("name") String name,
            @Field("hobby") String hobby
    );
}