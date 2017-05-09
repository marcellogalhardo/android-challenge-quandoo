package com.marcellogalhardo.quandoo;

import com.marcellogalhardo.quandoo.customerlist.CustomerListContract;
import com.marcellogalhardo.quandoo.customerlist.CustomerListPresenter;
import com.marcellogalhardo.quandoo.data.Customer;
import com.marcellogalhardo.quandoo.data.store.CustomerRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Maybe;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerListUnitTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerListContract.View view;

    private CustomerListPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new CustomerListPresenter(repository);
        presenter.bindView(view);
    }

    @After
    public void tearDown() {
        presenter.unbindView();
    }

    @Test
    public void getSuccessWhenTryToCallGetEventsFromRepositoryAndShowContentLayout() {
        when(repository.getCustomers()).then(invocation -> createFakeList());
        presenter.getCustomers();
        verify(view, times(1)).showContentLayout();
    }

    @Test
    public void getErrorWhenTryToCallGetCustomersFromRepositoryAndShowErrorLayout() {
        when(repository.getCustomers()).then(invocation -> createGenericError());
        presenter.getCustomers();
        verify(view, times(1)).showErrorLayout();
    }

    private Maybe<List<Customer>> createFakeList() {
        List<Customer> list = new ArrayList<>(9);
        list.add(new Customer(1, "Marcello", "Galhardo"));
        list.add(new Customer(2, "Marcello", "Galhardo"));
        list.add(new Customer(3, "Marcello", "Galhardo"));
        list.add(new Customer(4, "Marcello", "Galhardo"));
        list.add(new Customer(5, "Marcello", "Galhardo"));
        list.add(new Customer(6, "Marcello", "Galhardo"));
        list.add(new Customer(7, "Marcello", "Galhardo"));
        list.add(new Customer(8, "Marcello", "Galhardo"));
        list.add(new Customer(9, "Marcello", "Galhardo"));
        return Maybe.just(list);
    }

    private Maybe createGenericError() {
        return Maybe.error(new Exception());
    }

}