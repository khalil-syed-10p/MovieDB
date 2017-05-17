package com.khalil.moviedb.repositories;

import com.khalil.moviedb.network.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 5/17/17.
 */

public abstract class BasePagedRepository<T> {

    protected final ServiceFactory serviceFactory;
    protected final DataRequester<T> dataRequester;

    protected int currentPage;
    protected int totalPages;
    protected int totalData;

    protected List<T> list;

    public BasePagedRepository(ServiceFactory serviceFactory, DataRequester<T> dataRequester) {
        this.serviceFactory = serviceFactory;
        this.dataRequester = dataRequester;
    }

    public void loadData() {
        currentPage = 0;
        totalPages = 0;
        totalData = 0;
        if(list != null) {
            list.clear();
        }
        if(dataRequester != null) {
            dataRequester.willStartFreshLoad();
        }
        loadDataForPage(currentPage+1);
    }

    public void loadMore() {
        if(currentPage < 1) {
            loadData();
            return;
        }
        if(currentPage >= totalPages) {
            return;
        }

        loadDataForPage(currentPage+1);
    }

    protected abstract void loadDataForPage(int page);

    protected void onDataStateChanged(List<T> requestList, int currentPage, int totalPages) {

        this.totalPages = totalPages;
        this.currentPage = Math.min(currentPage, totalPages);
        if(requestList != null) {
            if(list == null) {
                list = new ArrayList<>(requestList);
            } else {
                list.addAll(requestList);
            }
        }

        if(dataRequester == null) {
            return;
        }

        dataRequester.onDataStateChanged(list, currentPage, totalPages);
    }

    protected void onRequestFailed(String errorMessage) {
        if(currentPage > 0) {
            currentPage--;
        }

        if(dataRequester == null) {
            return;
        }
        dataRequester.onDataRequestFailed(errorMessage.trim(), currentPage, totalPages);
    }

}
