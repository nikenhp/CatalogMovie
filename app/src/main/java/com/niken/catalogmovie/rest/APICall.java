package com.niken.catalogmovie.rest;


import com.niken.catalogmovie.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICall {
    @GET("movie/now_playing")
    Call<MovieModel> getNowPlaying(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("movie/upcoming")
    Call<MovieModel> getUpcoming(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("search/movie/")
    Call<MovieModel> getSearch(
            @Query("api_key") String apiKey,
            @Query("query") String q);

    @GET("popular/movie/")
    Call<MovieModel> getPopular (
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}