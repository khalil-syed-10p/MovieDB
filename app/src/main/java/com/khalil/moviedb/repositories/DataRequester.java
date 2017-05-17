package com.khalil.moviedb.repositories;

import java.util.List;

/**
 * Created on 5/17/17.
 */

public interface DataRequester<T> {

    void onDataStateChanged(List<T> list, int currentPage, int totalPages);
    void onDataRequestFailed(String errorMessage, int currentPage, int totalPages);
    void willStartFreshLoad();
}
