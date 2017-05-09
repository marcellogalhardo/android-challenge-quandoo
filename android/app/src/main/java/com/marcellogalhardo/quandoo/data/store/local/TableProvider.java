package com.marcellogalhardo.quandoo.data.store.local;

import com.marcellogalhardo.quandoo.data.Table;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

public class TableProvider {

    private static final String TAG = TableProvider.class.getSimpleName();

    public Flowable<List<Table>> update(List<Table> tables) {
        return Flowable.create(e -> {
            try {
                Hawk.delete(TAG);
                Hawk.put(TAG, tables);
                e.onNext(tables);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

    public Flowable<List<Table>> add(List<Table> tables) {
        return Flowable.create(e -> {
            try {
                Hawk.put(TAG, tables);
                e.onNext(tables);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

    public Flowable<List<Table>> getAll() {
        List<Table> tables = Hawk.get(TAG);
        if (tables != null) {
            return Flowable.just(tables);
        }
        return Flowable.just(new ArrayList<>());
    }

    public Flowable<Void> delete() {
        return Flowable.create(e -> {
            try {
                Hawk.delete(TAG);
                e.onNext(null);
                e.onComplete();
            } catch (Exception exception) {
                e.onError(exception);
            }
        }, BackpressureStrategy.DROP);
    }

}
