package com.marcellogalhardo.quandoo.data.store;

import com.marcellogalhardo.quandoo.data.store.local.CustomerProvider;
import com.marcellogalhardo.quandoo.data.store.local.TableProvider;
import com.marcellogalhardo.quandoo.data.store.remote.CustomerService;
import com.marcellogalhardo.quandoo.data.store.remote.TableService;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class StoreModule {

    @Provides
    @Reusable
    public CustomerRepository provideCustomerRepository(CustomerProvider provider, CustomerService service) {
        return new CustomerRepository(provider, service);
    }

    @Provides
    @Reusable
    public TableRepository provideTableRepository(TableProvider provider, TableService service) {
        return new TableRepository(provider, service);
    }

}
