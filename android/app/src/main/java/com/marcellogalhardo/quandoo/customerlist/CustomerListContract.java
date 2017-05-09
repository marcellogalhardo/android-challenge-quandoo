package com.marcellogalhardo.quandoo.customerlist;

import com.marcellogalhardo.quandoo.data.Customer;

import java.util.List;

public interface CustomerListContract {

    interface Presenter {

        void bindView(CustomerListContract.View view);

        void unbindView();

        void getCustomers();

    }

    interface View {

        void showErrorLayout();

        void showContentLayout();

        void showLoadingLayout();

        void setCustomers(List<Customer> customers);

    }

}
