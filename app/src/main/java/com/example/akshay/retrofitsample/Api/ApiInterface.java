package com.example.akshay.retrofitsample.Api;


import com.example.akshay.retrofitsample.Model.Example;
import com.example.akshay.retrofitsample.Model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<Example> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
