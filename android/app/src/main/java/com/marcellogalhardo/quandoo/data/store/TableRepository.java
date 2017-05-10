package com.marcellogalhardo.quandoo.data.store;

import com.marcellogalhardo.quandoo.data.Table;
import com.marcellogalhardo.quandoo.data.store.local.TableProvider;
import com.marcellogalhardo.quandoo.data.store.remote.TableService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TableRepository {

    private TableProvider provider;
    private TableService service;

    @Inject
    public TableRepository(TableProvider provider, TableService service) {
        this.provider = provider;
        this.service = service;
    }

    public Flowable<List<Table>> updateTables(List<Table> tables) {
        return provider.update(tables);
    }

    @SuppressWarnings("unchecked")
    public Maybe<List<Table>> getTables() {
        Flowable<List<Table>> local = getTablesLocal();
        Flowable<List<Table>> remote = getTablesRemote();

        return Flowable.concatArray(local, remote)
                .filter(list -> !list.isEmpty())
                .firstElement();
    }

    public Flowable<List<Table>> fetchTables() {
        return getTablesRemote();
    }

    private Flowable<List<Table>> getTablesLocal() {
        return provider.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Flowable<List<Table>> getTablesRemote() {
        return service.getTableMap()
                .cache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(reservations -> {
                    List<Table> tables = new ArrayList<>();
                    for (int i = 0; i < reservations.size(); i++) {
                        Boolean reservation = reservations.get(i);
                        Table table = new Table();
                        table.setNumber(i + 1);
                        table.setAvailable(!reservation);
                        tables.add(table);
                    }
                    return tables;
                })
                .flatMap(tables -> provider.update(tables));
    }

}
