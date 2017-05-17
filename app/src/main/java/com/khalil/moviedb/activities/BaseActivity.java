package com.khalil.moviedb.activities;

import android.support.v7.app.AppCompatActivity;

import com.khalil.moviedb.MovieDbApp;
import com.khalil.moviedb.network.ServiceFactory;

/**
 * Created on 5/17/17.
 */

public class BaseActivity extends AppCompatActivity{

    protected ServiceFactory getServiceFactory() {
        return MovieDbApp.getInstance().getServiceFactory();
    }
}
