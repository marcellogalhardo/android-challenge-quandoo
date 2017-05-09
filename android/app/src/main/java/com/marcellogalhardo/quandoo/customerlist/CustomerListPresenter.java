package com.marcellogalhardo.quandoo.customerlist;

import com.marcellogalhardo.quandoo.data.store.CustomerRepository;

import javax.inject.Inject;

public class CustomerListPresenter implements CustomerListContract.Presenter {

    private CustomerListContract.View view;

    private CustomerRepository customerRepository;

    @Inject
    public CustomerListPresenter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void bindView(CustomerListContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void getCustomers() {
        customerRepository.getCustomers()
                .subscribe(customers -> {
                    view.setCustomers(customers);
                    view.showContentLayout();
                }, throwable -> {
                    view.showErrorLayout();
                });
    }
}
