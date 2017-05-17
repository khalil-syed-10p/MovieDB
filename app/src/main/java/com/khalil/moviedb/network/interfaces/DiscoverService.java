package com.khalil.moviedb.network.interfaces;

import com.khalil.moviedb.entities.DiscoverMovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created on 5/17/17.
 */

public interface DiscoverService {

    @GET("discover/movie")
    Call<DiscoverMovieResponse> fetchMovies(@Query("page") int page, @Query("primary_release_date.gte") String date);
}
