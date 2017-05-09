package com.marcellogalhardo.quandoo.data.store.remote;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import retrofit2.Retrofit;

@Module
public class ServiceModule {

    @Reusable
    @Provides
    CustomerService provideCustomerService(Retrofit retrofit) {
        return retrofit.create(CustomerService.class);
    }

    @Reusable
    @Provides
    TableService provideTableService(Retrofit retrofit) {
        return retrofit.create(TableService.class);
    }

}
