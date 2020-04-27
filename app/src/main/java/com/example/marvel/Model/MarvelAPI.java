package com.example.marvel.Model;



import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MarvelAPI {
    @GET("film.json")
    Call<List<Marvel>> getMarvel  ();
}