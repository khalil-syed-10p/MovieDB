package com.khalil.moviedb.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.khalil.moviedb.R;
import com.khalil.moviedb.entities.MovieEntity;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends BaseActivity {

    private ImageView imgPoster;
    private TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgPoster = (ImageView) findViewById(R.id.imgPoster);
        txtTitle = (TextView) findViewById(R.id.txtTitle);

        MovieEntity movieEntity = getIntent().getParcelableExtra(MovieEntity.KEY_EXTRA);
        updateUI(movieEntity);
    }

    private void updateUI(MovieEntity movieEntity) {
        if(movieEntity == null) {
            return;
        }

        Picasso.with(this).load(movieEntity.getPosterPath()).into(imgPoster);
        txtTitle.setText(movieEntity.getTitle());
    }
}
