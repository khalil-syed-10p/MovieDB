package com.khalil.moviedb.entities;

import java.util.List;

/**
 * Created on 5/17/17.
 */

public class DiscoverMovieResponse {

    int page;
    int totalPages;
    int totalResults;

    List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public boolean hasResults() {
        return (results != null) && !results.isEmpty();
    }

    public int getCurrentPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }
}
