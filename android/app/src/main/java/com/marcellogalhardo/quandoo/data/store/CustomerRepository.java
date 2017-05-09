package com.marcellogalhardo.quandoo.data.store;

import com.marcellogalhardo.quandoo.data.Customer;
import com.marcellogalhardo.quandoo.data.store.local.CustomerProvider;
import com.marcellogalhardo.quandoo.data.store.remote.CustomerService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CustomerRepository {

    private CustomerProvider provider;
    private CustomerService service;

    @Inject
    public CustomerRepository(CustomerProvider provider, CustomerService service) {
        this.provider = provider;
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    public Maybe<List<Customer>> getCustomers() {
        Flowable<List<Customer>> local = getCustomersLocal();
        Flowable<List<Customer>> remote = getCustomersRemote();

        return Flowable.concatArray(local, remote)
                .filter(list -> !list.isEmpty())
                .firstElement();
    }

    private Flowable<List<Customer>> getCustomersLocal() {
        return provider.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<List<Customer>> getCustomersRemote() {
        return service.getCustomerList()
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(customers -> provider.update(customers));
    }

}
