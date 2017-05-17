package com.khalil.moviedb.network;

import android.content.Context;

import com.khalil.moviedb.network.interfaces.DiscoverService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 5/16/17.
 */

public class ServiceFactory {

    protected final Map<Class<?>, Object> instances = new ConcurrentHashMap<>();

    public final void initialize(Context context) {

        RetrofitAdapter.getInstance().initialize(context);
    }

    public DiscoverService getDiscoverService() {
         return loadService(DiscoverService.class);
    }


    /**
     * Use this method to create and load your service interfaces
     *
     * @param serviceClass Service Interface that you want to load
     * @return Service implementation
     */

    private <S> S loadService(Class<S> serviceClass) {
        if (!instances.containsKey(serviceClass)) {
            instances.put(serviceClass, RetrofitAdapter.getInstance().loadService(serviceClass));
        }
        return (S) instances.get(serviceClass);
    }
}
