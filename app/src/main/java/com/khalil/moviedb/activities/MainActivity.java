package com.khalil.moviedb.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.khalil.moviedb.R;
import com.khalil.moviedb.adapters.MoviesRecyclerAdapter;
import com.khalil.moviedb.adapters.MoviesAdapterListener;
import com.khalil.moviedb.components.DatePickerFragment;
import com.khalil.moviedb.components.DateSelectionListener;
import com.khalil.moviedb.entities.MovieEntity;
import com.khalil.moviedb.repositories.DataRequester;
import com.khalil.moviedb.repositories.MoviesRepository;

import java.util.List;

public class MainActivity extends BaseActivity implements DataRequester<MovieEntity>, MoviesAdapterListener, DateSelectionListener {

    private MoviesRepository moviesRepository;
    private MoviesRecyclerAdapter moviesRecyclerAdapter;

    private Button btnDatePicker;
    private TextView txtLoadData;
    private TextView txtNoData;
    private RecyclerView recyclerMovies;

    private DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        txtLoadData = (TextView) findViewById(R.id.txtLoading);
        txtNoData = (TextView) findViewById(R.id.txtNoData);
        recyclerMovies = (RecyclerView) findViewById(R.id.recyclerMovies);

        moviesRecyclerAdapter = new MoviesRecyclerAdapter(this);
        recyclerMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerMovies.setAdapter(moviesRecyclerAdapter);
        recyclerMovies.addItemDecoration(new DividerItemDecoration(this, 1));

        moviesRepository = new MoviesRepository(getServiceFactory(), this);
        moviesRepository.loadData();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDateFilterTapped();
            }
        });
    }

    @Override
    public void onDataStateChanged(List<MovieEntity> list, int currentPage, int totalPages) {
        if((list == null) || list.isEmpty()) {
            noDataAvailable();
            return;
        }
        recyclerMovies.setVisibility(View.VISIBLE);
        txtLoadData.setVisibility(View.GONE);
        txtNoData.setVisibility(View.GONE);
        moviesRecyclerAdapter.updateData(list);
    }

    private void noDataAvailable() {
        recyclerMovies.setVisibility(View.GONE);
        txtLoadData.setVisibility(View.GONE);
        txtNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDataRequestFailed(String errorMessage, int currentPage, int totalPages) {
        if((errorMessage == null)
                || !TextUtils.isEmpty(errorMessage.trim())) {
            return;
        }

        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void willStartFreshLoad() {
        recyclerMovies.setVisibility(View.GONE);
        txtLoadData.setVisibility(View.VISIBLE);
        txtNoData.setVisibility(View.GONE);
    }

    @Override
    public void aboutToReachEndOfTheList(int position) {
        if(moviesRepository == null) {
            return;
        }
        moviesRepository.loadMore();
    }

    @Override
    public void onItemClicked(MovieEntity movieEntity) {
        navigateToDetailActivity(movieEntity);
    }

    private void navigateToDetailActivity(MovieEntity movieEntity) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieEntity.KEY_EXTRA, movieEntity);
        startActivity(intent);
    }

    public void onDateFilterTapped () {
        if(datePickerFragment == null) {
            datePickerFragment = DatePickerFragment.newInstance(this);
        }
        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSelected(String date) {
        btnDatePicker.setText("Selected Date:" + date);
        if(moviesRepository == null) {
            return;
        }
        moviesRepository.dateChanged(date);
    }
}
