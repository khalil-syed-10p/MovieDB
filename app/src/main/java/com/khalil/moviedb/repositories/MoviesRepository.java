package com.khalil.moviedb.repositories;

import com.khalil.moviedb.entities.DiscoverMovieResponse;
import com.khalil.moviedb.entities.MovieEntity;
import com.khalil.moviedb.network.ServiceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 5/17/17.
 */

public class MoviesRepository extends BasePagedRepository<MovieEntity> {

    private String selectedDate;

    public MoviesRepository(ServiceFactory serviceFactory, DataRequester<MovieEntity> dataRequester) {
        super(serviceFactory, dataRequester);
    }

    public void dateChanged(String date) {
        this.selectedDate = date;
        loadData();
    }

    @Override
    protected void loadDataForPage(int page) {
        serviceFactory.getDiscoverService().fetchMovies(page, selectedDate).enqueue(new Callback<DiscoverMovieResponse>() {
            @Override
            public void onResponse(Call<DiscoverMovieResponse> call, Response<DiscoverMovieResponse> response) {

                DiscoverMovieResponse discoverMovieResponse = response.body();
                onDataStateChanged(discoverMovieResponse);

                if((discoverMovieResponse != null)
                        && discoverMovieResponse.hasResults()) {
                    return;
                }
                onRequestFailed("");

            }

            @Override
            public void onFailure(Call<DiscoverMovieResponse> call, Throwable t) {
                onDataStateChanged(list, currentPage, totalPages);
                onRequestFailed(t.getMessage());
            }
        });
    }

    private void onDataStateChanged(DiscoverMovieResponse discoverMovieResponse) {
        if(discoverMovieResponse == null) {
            return;
        }
        onDataStateChanged(discoverMovieResponse.getResults(), discoverMovieResponse.getCurrentPage(), discoverMovieResponse.getTotalPages());
    }
}
