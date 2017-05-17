package com.khalil.moviedb.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.khalil.moviedb.R;
import com.khalil.moviedb.entities.MovieEntity;
import com.khalil.moviedb.viewholders.MovieViewHolder;

import java.util.List;

/**
 * Created on 5/17/17.
 */

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<MovieEntity> movieEntities;
    private final MoviesAdapterListener moviesAdapterListener;

    private static final int END_THRESHOLD = 3;

    public MoviesRecyclerAdapter(List<MovieEntity> movieEntities, MoviesAdapterListener moviesAdapterListener) {
        this.movieEntities = movieEntities;
        this.moviesAdapterListener = moviesAdapterListener;
    }

    public MoviesRecyclerAdapter(MoviesAdapterListener moviesAdapterListener) {
        this.moviesAdapterListener = moviesAdapterListener;
    }

    public void setMovieEntities(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
    }

    public void updateData(List<MovieEntity> movieEntities) {
        this.movieEntities = movieEntities;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view, moviesAdapterListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if((movieEntities == null)
                || movieEntities.isEmpty()) {
            return;
        }
        if((moviesAdapterListener != null) && ((movieEntities.size() - position) == END_THRESHOLD)) {
            moviesAdapterListener.aboutToReachEndOfTheList(position);
        }
        holder.setMovie(movieEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return (movieEntities != null) ? movieEntities.size() : 0;
    }
}
