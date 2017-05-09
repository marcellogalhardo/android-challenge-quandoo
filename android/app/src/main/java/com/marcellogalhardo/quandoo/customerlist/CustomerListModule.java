package com.marcellogalhardo.quandoo.customerlist;

import com.marcellogalhardo.quandoo.data.store.CustomerRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CustomerListModule {

    @Provides
    @Singleton
    public CustomerListContract.Presenter provideCustomerListPresenter(CustomerRepository customerRepository) {
        return new CustomerListPresenter(customerRepository);
    }

}
