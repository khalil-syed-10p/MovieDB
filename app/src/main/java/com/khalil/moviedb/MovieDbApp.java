package com.khalil.moviedb;

import android.app.Application;

import com.khalil.moviedb.network.ServiceFactory;

/**
 * Created on 5/17/17.
 */

public class MovieDbApp extends Application {

    private ServiceFactory serviceFactory;

    private static MovieDbApp instance;

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }

    public static MovieDbApp getInstance() {
        return instance;
    }

    private void initServiceFactory() {
        serviceFactory = new ServiceFactory();
        serviceFactory.initialize(this);
    }

    public ServiceFactory getServiceFactory() {
        if(serviceFactory == null) {
            initServiceFactory();
        }
        return serviceFactory;
    }
}
