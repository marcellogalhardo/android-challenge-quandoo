package com.marcellogalhardo.quandoo.tablemap;

import com.marcellogalhardo.quandoo.data.store.TableRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TableMapModule {

    @Provides
    @Singleton
    public TableMapContract.Presenter provideTableMapPresenter(TableRepository tableRepository) {
        return new TableMapPresenter(tableRepository);
    }
}
