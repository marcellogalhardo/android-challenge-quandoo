package com.marcellogalhardo.quandoo.data.store.local;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class ProviderModule {

    @Reusable
    @Provides
    CustomerProvider provideCustomerProvider() {
        return new CustomerProvider();
    }

    @Reusable
    @Provides
    TableProvider provideTableProvider() {
        return new TableProvider();
    }

}