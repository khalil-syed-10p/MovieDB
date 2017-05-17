package com.khalil.moviedb.network;

import android.content.Context;

import com.khalil.moviedb.MovieDbApp;
import com.khalil.moviedb.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 5/17/17.
 */

public final class RetrofitAdapter implements Interceptor {

    private Retrofit retrofit;
    private static RetrofitAdapter retrofitAdapter;

    private RetrofitAdapter() {

    }

    public static RetrofitAdapter getInstance() {
        if(retrofitAdapter == null) {
            retrofitAdapter = new RetrofitAdapter();
        }
        return retrofitAdapter;
    }

    public void initialize(Context context) {

        if(retrofit != null) {
            return;
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.api_url))
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();
    }

    private Gson getGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return okHttpClient.newBuilder()
                .addInterceptor(this)
                .build();
    }

    public <S> S loadService(Class<S> serviceClass) {
        if(retrofit == null) {
            initialize(MovieDbApp.getInstance());
        }
        return retrofit.create(serviceClass);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url();
        HttpUrl modifiedUrl = httpUrl.newBuilder()
                .addQueryParameter("api_key", MovieDbApp.getInstance().getString(R.string.api_key)).build();
        Request modifiedRequest = request.newBuilder()
                .url(modifiedUrl).build();
        return chain.proceed(modifiedRequest);
    }
}
