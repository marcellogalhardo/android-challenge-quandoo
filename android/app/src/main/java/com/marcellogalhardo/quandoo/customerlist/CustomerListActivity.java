package com.marcellogalhardo.quandoo.customerlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;

import com.marcellogalhardo.quandoo.R;
import com.marcellogalhardo.quandoo.base.BaseActivity;
import com.marcellogalhardo.quandoo.data.Customer;
import com.marcellogalhardo.quandoo.tablemap.TableMapNavigation;
import com.marcellogalhardo.quandoo.utils.ViewFlipperUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerListActivity extends BaseActivity implements CustomerListContract.View {

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.recycler_view_customers)
    RecyclerView recyclerViewCustomers;

    @BindView(R.id.error_layout)
    View errorLayout;

    @BindView(R.id.loading_layout)
    View loadingLayout;

    @Inject
    CustomerListPresenter customerListPresenter;

    @Inject
    ViewFlipperUtils viewFlipperUtils;

    private CustomerListAdapter customerListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        ButterKnife.bind(this);
        getMainComponent().inject(this);
        customerListPresenter.bindView(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_customer_list, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        setupSearchView(item, searchView);
        return true;
    }

    private void init() {
        setupToolbar();
        setupRecyclerViewCustomers();
        showLoadingLayout();
        customerListPresenter.getCustomers();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.customer_list_title);
        }
    }

    @Override
    public void showErrorLayout() {
        viewFlipperUtils.setDisplayedChild(viewFlipper, errorLayout);
    }

    @Override
    public void showContentLayout() {
        viewFlipperUtils.setDisplayedChild(viewFlipper, recyclerViewCustomers);
    }

    @Override
    public void showLoadingLayout() {
        viewFlipperUtils.setDisplayedChild(viewFlipper, loadingLayout);
    }

    @Override
    public void setCustomers(List<Customer> customers) {
        customerListAdapter.setCustomers(customers);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.retry_button)
    void onRetryClicked() {
        showLoadingLayout();
        customerListPresenter.getCustomers();
    }

    private void setupRecyclerViewCustomers() {
        customerListAdapter = new CustomerListAdapter();
        customerListAdapter.setOnCustomerClickedListener((position, customer) -> {
            TableMapNavigation.start(CustomerListActivity.this, customer);
        });
        recyclerViewCustomers.setAdapter(customerListAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCustomers.setLayoutManager(layoutManager);
    }

    public void setupSearchView(MenuItem item, SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customerListAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
