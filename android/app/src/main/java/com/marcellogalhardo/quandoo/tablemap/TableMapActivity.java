package com.marcellogalhardo.quandoo.tablemap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.marcellogalhardo.quandoo.R;
import com.marcellogalhardo.quandoo.base.BaseActivity;
import com.marcellogalhardo.quandoo.data.Customer;
import com.marcellogalhardo.quandoo.data.Table;
import com.marcellogalhardo.quandoo.utils.ViewFlipperUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TableMapActivity extends BaseActivity implements TableMapContract.View {

    private static int GRID_SPAN_COUNT = 4;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    @BindView(R.id.customer_name)
    TextView customerName;

    @BindView(R.id.recycler_view_tables)
    RecyclerView recyclerViewTables;

    @BindView(R.id.error_layout)
    View errorLayout;

    @BindView(R.id.loading_layout)
    View loadingLayout;

    @Inject
    TableMapPresenter tableMapPresenter;

    @Inject
    ViewFlipperUtils viewFlipperUtils;

    private TableMapAdapter tableMapAdapter;
    private Customer customer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_map);
        ButterKnife.bind(this);
        getMainComponent().inject(this);
        tableMapPresenter.bindView(this);
        loadArguments();
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadArguments() {
        Intent intent = getIntent();
        if (intent != null) {
            customer = intent.getParcelableExtra(Customer.TAG);
        }
    }

    private void init() {
        bindView();
        setupToolbar();
        setupRecyclerViewTables();
        showLoadingLayout();
        tableMapPresenter.getTables();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(R.string.table_map_title);
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }
    }

    private void bindView() {
        if (customer != null) {
            customerName.setText(customer.getName());
        }
    }

    @Override
    public void showErrorLayout() {
        viewFlipperUtils.setDisplayedChild(viewFlipper, errorLayout);
    }

    @Override
    public void showContentLayout() {
        viewFlipperUtils.setDisplayedChild(viewFlipper, recyclerViewTables);
    }

    @Override
    public void showLoadingLayout() {
        viewFlipperUtils.setDisplayedChild(viewFlipper, loadingLayout);
    }

    @Override
    public void setTables(List<Table> tables) {
        tableMapAdapter.setTables(tables);
    }

    @Override
    public void showUpdateTableSuccessMessage() {
        Toast.makeText(this, "Your Table has been booked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUpdateTableFailureMessage() {
        Toast.makeText(this, "Unable to book the selected table", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        finish();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.retry_button)
    void onRetryClicked() {
        showLoadingLayout();
        tableMapPresenter.getTables();
    }

    public void setupRecyclerViewTables() {
        tableMapAdapter = new TableMapAdapter();
        tableMapAdapter.setOnTableClickedListener((position, table) -> {
            TableMapActivity activity = TableMapActivity.this;
            if (table.isAvailable()) {
                TableMapDialog.showAvailableDialog(activity, table, (dialogInterface, i) -> {
                    table.setAvailable(false);
                    table.setCustomer(customer);
                    tableMapPresenter.updateTables(tableMapAdapter.getTables());
                });
            } else {
                TableMapDialog.showUnavailableDialog(activity, table);
            }
        });
        recyclerViewTables.setAdapter(tableMapAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, GRID_SPAN_COUNT);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewTables.setLayoutManager(layoutManager);
    }

}
