package com.marcellogalhardo.quandoo.data.store.remote;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface TableService {

    @GET("table-map.json")
    Flowable<List<Boolean>> getTableMap();

}
