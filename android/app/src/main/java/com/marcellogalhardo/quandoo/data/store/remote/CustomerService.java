package com.marcellogalhardo.quandoo.data.store.remote;

import com.marcellogalhardo.quandoo.data.Customer;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface CustomerService {

    @GET("customer-list.json")
    Flowable<List<Customer>> getCustomerList();
}