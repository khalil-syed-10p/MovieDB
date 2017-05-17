package com.khalil.moviedb.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.khalil.moviedb.R;
import com.khalil.moviedb.adapters.MoviesAdapterListener;
import com.khalil.moviedb.entities.MovieEntity;

/**
 * Created on 5/17/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final TextView txtName;
    private final MoviesAdapterListener moviesAdapterListener;
    private MovieEntity movieEntity;

    public MovieViewHolder(View itemView, MoviesAdapterListener moviesAdapterListener) {
        super(itemView);
        this.moviesAdapterListener = moviesAdapterListener;
        txtName = (TextView) itemView.findViewById(R.id.txtName);
        itemView.setOnClickListener(this);
    }

    public void setMovie(MovieEntity movie) {
        movieEntity = movie;
        if(movie == null) {
            return;
        }

        txtName.setText(movie.getTitle());
    }

    @Override
    public void onClick(View view) {
        if(moviesAdapterListener == null) {
            return;
        }

        moviesAdapterListener.onItemClicked(movieEntity);
    }
}
