package com.marcellogalhardo.quandoo.tablemap;

import com.marcellogalhardo.quandoo.data.Table;
import com.marcellogalhardo.quandoo.data.store.TableRepository;

import java.util.List;

import javax.inject.Inject;

public class TableMapPresenter implements TableMapContract.Presenter {

    private TableMapContract.View view;

    private TableRepository tableRepository;

    @Inject
    public TableMapPresenter(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public void bindView(TableMapContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void getTables() {
        tableRepository.getTables()
                .subscribe(tables -> {
                    view.setTables(tables);
                    view.showContentLayout();
                }, throwable -> {
                    view.showErrorLayout();
                });
    }

    @Override
    public void updateTables(List<Table> tables) {
        tableRepository.updateTables(tables)
                .subscribe(tables1 -> {
                    view.showUpdateTableSuccessMessage();
                    view.close();
                }, throwable -> {
                    view.showUpdateTableFailureMessage();
                });
    }

}
