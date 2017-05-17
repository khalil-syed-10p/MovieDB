package com.khalil.moviedb.adapters;


import com.khalil.moviedb.entities.MovieEntity;

/**
 * Created on 5/17/17.
 */

public interface MoviesAdapterListener {

    void aboutToReachEndOfTheList(int position);
    void onItemClicked(MovieEntity movieEntity);
}
