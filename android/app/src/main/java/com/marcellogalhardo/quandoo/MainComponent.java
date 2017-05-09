package com.marcellogalhardo.quandoo;

import com.marcellogalhardo.quandoo.customerlist.CustomerListActivity;
import com.marcellogalhardo.quandoo.customerlist.CustomerListModule;
import com.marcellogalhardo.quandoo.data.store.StoreModule;
import com.marcellogalhardo.quandoo.data.store.local.ProviderModule;
import com.marcellogalhardo.quandoo.data.store.remote.ServiceModule;
import com.marcellogalhardo.quandoo.tablemap.TableMapActivity;
import com.marcellogalhardo.quandoo.tablemap.TableMapModule;
import com.marcellogalhardo.quandoo.tablemap.TableMapTaskService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        StoreModule.class,
        ProviderModule.class,
        ServiceModule.class,
        CustomerListModule.class,
        TableMapModule.class})
public interface MainComponent {

    void inject(CustomerListActivity activity);

    void inject(TableMapActivity activity);

    void inject(TableMapTaskService service);

}